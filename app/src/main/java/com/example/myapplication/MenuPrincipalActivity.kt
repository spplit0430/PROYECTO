package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_principal)

        // Recibir los datos enviados desde LoginActivity
        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val correo = intent.getStringExtra("correo") ?: ""
        val fechaNacimiento = intent.getStringExtra("fechaNacimiento") ?: ""

        // Botón TESTS para ir al PerfilUsuarioActivity
        val btnTests = findViewById<Button>(R.id.btn_tests)
        btnTests.setOnClickListener {
            val intent = Intent(this, PerfilUsuarioActivity::class.java).apply {
                putExtra("nombre", nombre)
                putExtra("apellido", apellido)
                putExtra("correo", correo)
                putExtra("fechaNacimiento", fechaNacimiento)
            }
            startActivity(intent)
        }
        val btnSalir = findViewById<ImageView>(R.id.salida_olvido2)
        btnSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("¿Salir al inicio de sesión?")
            builder.setMessage("¿Estás seguro de que deseas cerrar sesión y volver al login?")
            builder.setPositiveButton("Sí") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }
}
