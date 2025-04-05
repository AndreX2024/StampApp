package com.example.tallerdn6a.fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tallerdn6a.R

class EditarPerfilFragment : Fragment() {

    private lateinit var editText_username: EditText
    private lateinit var editText_userlastname: EditText
    private lateinit var editText_email: EditText
    private lateinit var editText_phone: EditText
    private lateinit var btnGuardarCambios: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_perfil, container, false)

        editText_username = view.findViewById(R.id.et_username_edit_profile)
        editText_userlastname = view.findViewById(R.id.et_userlastname_edit_profile)
        editText_email = view.findViewById(R.id.et_email_edit_profile)
        editText_phone = view.findViewById(R.id.et_phone_edit_profile)
        btnGuardarCambios = view.findViewById(R.id.btnGuardarCambios)

        sharedPreferences = requireContext().getSharedPreferences("UserData", MODE_PRIVATE)

        cargarDatos()

        btnGuardarCambios.setOnClickListener {
            if (validarCampos()) {
                actualizarDatos()
                Toast.makeText(requireContext(), "Cambios guardados exitosamente!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.perfilFragment)
            }
        }

        return view
    }

    private fun cargarDatos() {
        editText_username.setText(sharedPreferences.getString("Nombres", ""))
        editText_userlastname.setText(sharedPreferences.getString("Apellidos", ""))
        editText_email.setText(sharedPreferences.getString("Correo Eléctronico", ""))
        editText_phone.setText(sharedPreferences.getString("Teléfono", ""))
    }

    private fun validarCampos(): Boolean {
        val nombres = editText_username.text.toString().trim()
        val apellidos = editText_userlastname.text.toString().trim()
        val email = editText_email.text.toString().trim()
        val phone = editText_phone.text.toString().trim()

        if (nombres.isEmpty()) {
            Toast.makeText(requireContext(), "El campo nombres es requerido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (apellidos.isEmpty()) {
            Toast.makeText(requireContext(), "El campo apellidos es requerido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isEmpty()) {
            Toast.makeText(requireContext(), "El campo correo electrónico es requerido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (phone.isEmpty()) {
            Toast.makeText(requireContext(), "El campo teléfono es requerido", Toast.LENGTH_SHORT).show()
            return false
        } else if (phone.length < 10) {
            Toast.makeText(requireContext(), "El número de teléfono debe tener al menos 10 dígitos", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun actualizarDatos() {
        val editor = sharedPreferences.edit()
        editor.putString("Nombres", editText_username.text.toString().trim())
        editor.putString("Apellidos", editText_userlastname.text.toString().trim())
        editor.putString("Correo Eléctronico", editText_email.text.toString().trim())
        editor.putString("Teléfono", editText_phone.text.toString().trim())
        editor.apply()
    }
}
