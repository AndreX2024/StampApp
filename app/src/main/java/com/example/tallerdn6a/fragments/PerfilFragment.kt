package com.example.tallerdn6a.fragments


import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tallerdn6a.R

class PerfilFragment : Fragment() {

    private lateinit var textViewUsername: TextView
    private lateinit var textViewUserLastname: TextView
    private lateinit var textViewEmail: TextView
    private lateinit var textViewPhone: TextView
    private lateinit var btnEditarPerfil: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        textViewUsername = view.findViewById<TextView>(R.id.tv_username_profile)
        textViewUserLastname = view.findViewById<TextView>(R.id.tv_userlastname_profile)
        textViewEmail = view.findViewById<TextView>(R.id.tv_email_profile)
        textViewPhone = view.findViewById<TextView>(R.id.tv_phone_profile)

        sharedPreferences = requireContext().getSharedPreferences("UserData", MODE_PRIVATE)

        cargarDatos()

        btnEditarPerfil = view.findViewById(R.id.btnGuardarCambios)
        btnEditarPerfil.setOnClickListener {
            findNavController().navigate(R.id.editarPerfilFragment)
        }

        return view
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