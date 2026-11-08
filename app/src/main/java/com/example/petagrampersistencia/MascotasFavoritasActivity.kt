package com.example.petagrampersistencia

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MascotasFavoritasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favoritas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val favoritas = BaseDatos(this).use { it.ultimasCinco() }
        if (favoritas.isEmpty()) {
            Toast.makeText(this, "Todavía no le has dado rating a ninguna mascota", Toast.LENGTH_LONG).show()
        }

        val lista = findViewById<RecyclerView>(R.id.rvFavoritas)
        lista.layoutManager = LinearLayoutManager(this)
        lista.adapter = MascotaAdaptador(favoritas)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
