package com.example.petagrampersistencia

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ContactoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contacto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tilNombre = findViewById<TextInputLayout>(R.id.tilNombre)
        val tilCorreo = findViewById<TextInputLayout>(R.id.tilCorreo)
        val tilMensaje = findViewById<TextInputLayout>(R.id.tilMensaje)
        val etNombre = findViewById<TextInputEditText>(R.id.etNombre)
        val etCorreo = findViewById<TextInputEditText>(R.id.etCorreo)
        val etMensaje = findViewById<TextInputEditText>(R.id.etMensaje)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val mensaje = etMensaje.text.toString().trim()

            tilNombre.error = if (nombre.isEmpty()) "Escribe tu nombre" else null
            tilCorreo.error = if (correo.isEmpty()) "Escribe tu correo" else null
            tilMensaje.error = if (mensaje.isEmpty()) "Escribe un mensaje" else null
            if (nombre.isEmpty() || correo.isEmpty() || mensaje.isEmpty()) return@setOnClickListener

            val cuerpo = "Nombre: $nombre\nCorreo: $correo\n\nMensaje:\n$mensaje"

            btnEnviar.isEnabled = false
            Thread {
                try {
                    MailSender.enviar("Comentario desde Petagram", cuerpo)
                    runOnUiThread {
                        Toast.makeText(this, "Comentario enviado", Toast.LENGTH_LONG).show()
                        etNombre.text?.clear()
                        etCorreo.text?.clear()
                        etMensaje.text?.clear()
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this, "No se pudo enviar: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                } finally {
                    runOnUiThread { btnEnviar.isEnabled = true }
                }
            }.start()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
