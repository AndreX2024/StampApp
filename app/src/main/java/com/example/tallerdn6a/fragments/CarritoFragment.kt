package com.example.tallerdn6a.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tallerdn6a.R

class CarritoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carrito, container, false)
        val layoutCarrito = view.findViewById<LinearLayout>(R.id.layoutCarrito)

        val txtTotalProductos = view.findViewById<TextView>(R.id.txtTotalProductos)
        val txtTotalPrecio = view.findViewById<TextView>(R.id.txtTotalPrecio)

        val sharedPreferences = requireActivity().getSharedPreferences("carrito_prefs", Context.MODE_PRIVATE)
        val productos = sharedPreferences.getStringSet("carrito_items", emptySet()) ?: emptySet()

        var totalProductos = 0
        var precioTotal = 0.0

        if (productos.isEmpty()) {
            val emptyText = TextView(requireContext())
            emptyText.text = "Tu carrito está vacío."
            emptyText.textSize = 18f
            layoutCarrito.addView(emptyText)
        } else {
            for (productoString in productos) {
                val partes = productoString.split("|")

                // Validar que tenga nombre, cantidad y precio
                if (partes.size < 3) continue

                val nombre = partes[0]
                val cantidad = partes[1].toIntOrNull() ?: continue
                val precio = partes[2].toDoubleOrNull() ?: continue
                val subtotal = cantidad * precio

                val itemView = layoutInflater.inflate(R.layout.item_carrito, layoutCarrito, false)

                val textNombre = itemView.findViewById<TextView>(R.id.textNombre)
                val textCantidad = itemView.findViewById<TextView>(R.id.textCantidad)
                val textPrecio = itemView.findViewById<TextView>(R.id.textPrecio)
                val textSubtotal = itemView.findViewById<TextView>(R.id.textSubtotal)
                val btnEliminar = itemView.findViewById<Button>(R.id.btnEliminar)

                textNombre.text = "Producto: $nombre"
                textCantidad.text = "Cantidad: $cantidad"
                textPrecio.text = "Precio unitario: $${"%.2f".format(precio)}"
                textSubtotal.text = "Subtotal: $${"%.2f".format(subtotal)}"



                btnEliminar.setOnClickListener {
                    val editor = sharedPreferences.edit()
                    val nuevosProductos = productos.toMutableSet()
                    nuevosProductos.remove(productoString)
                    editor.putStringSet("carrito_items", nuevosProductos)
                    editor.apply()
                    layoutCarrito.removeView(itemView)
                }

                layoutCarrito.addView(itemView)

                totalProductos += cantidad
                precioTotal += subtotal

                txtTotalProductos.text = "Total de productos: $totalProductos"
                txtTotalPrecio.text = "Precio total: $${"%.2f".format(precioTotal)}"
            }

        }

        return view
    }
}
