package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registrarse)

        // Configurar el botón de salida (retorno al Login)
        val salirRegistro = findViewById<ImageView>(R.id.salida_olvido2)
        salirRegistro.setOnClickListener {
            finish() // Finaliza esta actividad y vuelve al Login (MainActivity)
        }
    }
}

