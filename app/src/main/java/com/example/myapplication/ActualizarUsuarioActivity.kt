package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class ActualizarUsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2_actualizar)

        val nombreText = findViewById<EditText>(R.id.nombre_actualizar_text)
        val apellidoText = findViewById<EditText>(R.id.apellido_actualizar_text)
        val correoText = findViewById<EditText>(R.id.correo_electronico_actualizar_text)
        val fechaNacimientoText = findViewById<EditText>(R.id.fecha_de_nacimiento_actualizar_text)

        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val correo = intent.getStringExtra("correo") ?: ""
        val fechaNacimiento = intent.getStringExtra("fechaNacimiento") ?: ""

        // Guardamos el correo original (el que ya estaba antes de modificar)
        val correoAnterior = correo.trim().lowercase()

        nombreText.setText(nombre)
        apellidoText.setText(apellido)
        correoText.setText(correo)
        fechaNacimientoText.setText(fechaNacimiento)

        val botonRetroceso = findViewById<ImageView>(R.id.salida_olvido2)
        botonRetroceso.setOnClickListener {
            finish()
        }

        val actualizarBtn = findViewById<Button>(R.id.login_button)
        actualizarBtn.setOnClickListener {
            val nuevoNombre = nombreText.text.toString().trim()
            val nuevoApellido = apellidoText.text.toString().trim()
            val nuevoCorreo = correoText.text.toString().trim().lowercase()
            val nuevaFechaNacimiento = fechaNacimientoText.text.toString().trim()

            if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoCorreo.isEmpty() || nuevaFechaNacimiento.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val url = "http://192.168.1.3/usuarios_api/actualizar_usuario.php"

            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                { response ->
                    Toast.makeText(this, response, Toast.LENGTH_SHORT).show()

                    // Redirigir a perfil con los datos actualizados
                    val intent = Intent(this, PerfilUsuarioActivity::class.java).apply {
                        putExtra("nombre", nuevoNombre)
                        putExtra("apellido", nuevoApellido)
                        putExtra("correo", nuevoCorreo)
                        putExtra("fechaNacimiento", nuevaFechaNacimiento)
                    }
                    startActivity(intent)
                    finish()
                },
                { error ->
                    Toast.makeText(this, "Error al actualizar: ${error.message}", Toast.LENGTH_LONG).show()
                }
            ) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["correo_anterior"] = correoAnterior
                    params["correo_electronico"] = nuevoCorreo // ← Aquí se usa el nombre correcto
                    params["nombre"] = nuevoNombre
                    params["apellido"] = nuevoApellido
                    params["fechaNacimiento"] = nuevaFechaNacimiento
                    return params
                }
            }

            val queue = Volley.newRequestQueue(this)
            queue.add(stringRequest)
        }
    }
}
