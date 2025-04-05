package com.example.tallerdn6a.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tallerdn6a.R

class ProductosFragment : Fragment() {

    private lateinit var btnAgregarCamiseta: Button

    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "carrito_prefs"
    private val KEY_CARRITO = "carrito_items"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        // Inicializa SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Referencia al botón
        btnAgregarCamiseta = view.findViewById<Button>(R.id.btn_agregar_camiseta)

        btnAgregarCamiseta.setOnClickListener {
            val nombre = "Camiseta Básica"
            val cantidad = 2
            val precio = 14.99
            val producto = "$nombre|$cantidad|$precio"
            agregarAlCarrito(producto)
        }


        return view
    }

    private fun agregarAlCarrito(producto: String) {
        val carritoActual = sharedPreferences.getStringSet(KEY_CARRITO, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        carritoActual.add(producto)

        sharedPreferences.edit()
            .putStringSet(KEY_CARRITO, carritoActual)
            .apply()

        Toast.makeText(requireContext(), "$producto agregado al carrito", Toast.LENGTH_SHORT).show()
    }
}
