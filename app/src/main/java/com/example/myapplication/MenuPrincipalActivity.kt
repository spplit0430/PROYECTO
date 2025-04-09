package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_principal)

        // 🔸 Recibir los datos enviados desde LoginActivity
        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val correo = intent.getStringExtra("correo") ?: ""
        val fechaNacimiento = intent.getStringExtra("fechaNacimiento") ?: ""

        // 🔸 Botón TESTS para ir al PerfilUsuarioActivity
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
    }
}
