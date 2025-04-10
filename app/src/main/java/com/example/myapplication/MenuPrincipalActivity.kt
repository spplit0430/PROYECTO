package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat

class MenuPrincipalActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var btnMenu: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_principal)

        // Inicializar el DrawerLayout y el botón del menú
        drawerLayout = findViewById(R.id.drawer_layout)
        btnMenu = findViewById(R.id.btn_menu)

        btnMenu.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.openDrawer(GravityCompat.END)
            } else {
                drawerLayout.closeDrawer(GravityCompat.END)
            }
        }

        // Recibir los datos enviados desde LoginActivity
        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val correo = intent.getStringExtra("correo") ?: ""
        val fechaNacimiento = intent.getStringExtra("fechaNacimiento") ?: ""

        // Mostrar el nombre en el TextView de bienvenida
        val textoBienvenida = findViewById<TextView>(R.id.text_bienvenida)
        textoBienvenida.text = "¡Bienvenido $nombre!"

        // Botón TESTS para ir al PerfilUsuarioActivity
        // Botón USUARIO (ahora es un TextView) para ir a PerfilUsuarioActivity
        val btnUsuario = findViewById<TextView>(R.id.btn_usuario)
        btnUsuario.setOnClickListener {
            val intent = Intent(this, PerfilUsuarioActivity::class.java).apply {
                putExtra("nombre", nombre)
                putExtra("apellido", apellido)
                putExtra("correo", correo)
                putExtra("fechaNacimiento", fechaNacimiento)
            }
            startActivity(intent)
        }


        // Botón para salir de sesión
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
