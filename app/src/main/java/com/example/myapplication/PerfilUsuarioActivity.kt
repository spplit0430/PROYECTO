package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PerfilUsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2_usuario)

        // Obtener datos desde el intent
        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val correo = intent.getStringExtra("correo") ?: ""
        val fechaNacimiento = intent.getStringExtra("fechaNacimiento") ?: ""

        // Referencias a los EditText
        val nombreText = findViewById<EditText>(R.id.nombre_text)
        val apellidoText = findViewById<EditText>(R.id.apellido_text)
        val correoText = findViewById<EditText>(R.id.correo_electronico_text)
        val fechaNacimientoText = findViewById<EditText>(R.id.fecha_de_nacimiento_text)

        // 🔸 Establecer los valores recibidos en los EditText
        nombreText.setText(nombre)
        apellidoText.setText(apellido)
        correoText.setText(correo)
        fechaNacimientoText.setText(fechaNacimiento)

        // ✅ Referencias a los TextView del LinearLayout superior (los que mostraban "David Garcia")
        val nombreLabel = findViewById<TextView>(R.id.nombre_label)
        val apellidoLabel = findViewById<TextView>(R.id.apellido_label)

        // ✅ Establecer los valores en los TextView superiores
        nombreLabel.text = nombre
        apellidoLabel.text = apellido

        // 🔸 Botón de retroceso (icono)
        val botonRetroceso = findViewById<ImageView>(R.id.salida_olvido2)
        botonRetroceso.setOnClickListener {
            finish() // Finaliza la actividad actual y regresa
        }

        // 🔸 Botón "Actualizar Información" → navegar a otra vista
        val actualizarBtn = findViewById<Button>(R.id.login_button)
        actualizarBtn.setOnClickListener {
            val intent = Intent(this, ActualizarUsuarioActivity::class.java)

            // 🔄 Enviar los datos actuales al nuevo Activity
            intent.putExtra("nombre", nombreText.text.toString())
            intent.putExtra("apellido", apellidoText.text.toString())
            intent.putExtra("correo", correoText.text.toString())
            intent.putExtra("fechaNacimiento", fechaNacimientoText.text.toString())

            startActivity(intent)
        }
    }
}
