package com.example.tallerdn6a.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tallerdn6a.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var textViewUsername: TextView
    private lateinit var textViewUserLastname: TextView
    private lateinit var textViewEmail: TextView
    private lateinit var textViewPhone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        textViewUsername = findViewById(R.id.tv_username_profile)
        textViewUserLastname = findViewById(R.id.tv_userlastname_profile)
        textViewEmail = findViewById(R.id.tv_email_profile)
        textViewPhone = findViewById(R.id.tv_phone_profile)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        cargarDatos()
    }

    private fun cargarDatos() {
        val nombre = sharedPreferences.getString("Nombres", "No disponible")
        val apellido = sharedPreferences.getString("Apellidos", "No disponible")
        val email = sharedPreferences.getString("Correo Eléctronico", "No disponible")
        val telefono = sharedPreferences.getString("Teléfono", "No disponible")

        textViewUsername.text = nombre
        textViewUserLastname.text = apellido
        textViewEmail.text = email
        textViewPhone.text = telefono
    }
}
