package com.example.petagrampersistencia

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(context: Context) :
    SQLiteOpenHelper(context, NOMBRE_BD, null, VERSION_BD) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLA_MASCOTA (" +
                "$COL_ID INTEGER PRIMARY KEY, " +
                "$COL_NOMBRE TEXT NOT NULL, " +
                "$COL_FOTO INTEGER NOT NULL, " +
                "$COL_RATING INTEGER NOT NULL, " +
                "$COL_FECHA INTEGER NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_MASCOTA")
        onCreate(db)
    }

    fun guardarRating(mascota: Mascota) {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put(COL_ID, mascota.id)
            put(COL_NOMBRE, mascota.nombre)
            put(COL_FOTO, mascota.foto)
            put(COL_RATING, mascota.rating)
            put(COL_FECHA, mascota.fechaRating)
        }
        db.insertWithOnConflict(TABLA_MASCOTA, null, valores, SQLiteDatabase.CONFLICT_REPLACE)
        db.execSQL(
            "DELETE FROM $TABLA_MASCOTA WHERE $COL_ID NOT IN (" +
                "SELECT $COL_ID FROM $TABLA_MASCOTA ORDER BY $COL_FECHA DESC LIMIT $MAXIMO)"
        )
    }

    fun ultimasCinco(): ArrayList<Mascota> {
        val lista = ArrayList<Mascota>()
        val cursor = readableDatabase.query(
            TABLA_MASCOTA, null, null, null, null, null,
            "$COL_FECHA DESC", MAXIMO.toString()
        )
        cursor.use {
            while (it.moveToNext()) {
                lista.add(
                    Mascota(
                        it.getInt(it.getColumnIndexOrThrow(COL_ID)),
                        it.getString(it.getColumnIndexOrThrow(COL_NOMBRE)),
                        it.getInt(it.getColumnIndexOrThrow(COL_FOTO)),
                        it.getInt(it.getColumnIndexOrThrow(COL_RATING)),
                        it.getLong(it.getColumnIndexOrThrow(COL_FECHA))
                    )
                )
            }
        }
        return lista
    }

    companion object {
        const val NOMBRE_BD = "petagram.db"
        const val VERSION_BD = 1
        const val MAXIMO = 5

        const val TABLA_MASCOTA = "mascota"
        const val COL_ID = "id"
        const val COL_NOMBRE = "nombre"
        const val COL_FOTO = "foto"
        const val COL_RATING = "rating"
        const val COL_FECHA = "fecha_rating"
    }
}
