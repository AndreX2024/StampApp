package com.example.tallerdn6a.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tallerdn6a.R

class CategoriasFragment : Fragment() {

    private lateinit var tvTshirtsCount: TextView
    private lateinit var tvPantsCount: TextView
    private lateinit var tvCapsCount: TextView
    private lateinit var tvJacketsCount: TextView
    private lateinit var tvShoesCount: TextView
    private lateinit var tvAccesoriesCount: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categorias, container, false)

        // Contadores de productos
        val tshirtsCount = 6
        val pantsCount = 4
        val capsCount = 7
        val jacketsCount = 5
        val shoesCount = 6
        val accesoriesCount = 3

        // Asignar valores a los TextView de cantidad
        tvTshirtsCount = view.findViewById(R.id.tv_tshirts_count)
        tvTshirtsCount.text = getString(R.string.category_product_count, tshirtsCount)
        tvPantsCount = view.findViewById(R.id.tv_pants_count)
        tvPantsCount.text = getString(R.string.category_product_count, pantsCount)
        tvCapsCount = view.findViewById(R.id.tv_caps_count)
        tvCapsCount.text = getString(R.string.category_product_count, capsCount)
        tvJacketsCount = view.findViewById(R.id.tv_jackets_count)
        tvJacketsCount.text = getString(R.string.category_product_count, jacketsCount)
        tvShoesCount = view.findViewById(R.id.tv_shoes_count)
        tvShoesCount.text = getString(R.string.category_product_count, shoesCount)
        tvAccesoriesCount = view.findViewById(R.id.tv_accessories_count)
        tvAccesoriesCount.text = getString(R.string.category_product_count, accesoriesCount)

        // Navegación con CardViews
        view.findViewById<CardView>(R.id.cv_tshirts_category).setOnClickListener {
            findNavController().navigate(R.id.camisetasFragment)
        }

        view.findViewById<CardView>(R.id.cv_pants_category).setOnClickListener {
            findNavController().navigate(R.id.pantalonesFragment)
        }

        view.findViewById<CardView>(R.id.cv_caps_category).setOnClickListener {
            findNavController().navigate(R.id.gorrasFragment)
        }

        view.findViewById<CardView>(R.id.cv_jackets_category).setOnClickListener {
            findNavController().navigate(R.id.chaquetasFragment)
        }

        view.findViewById<CardView>(R.id.cv_shoes_category).setOnClickListener {
            findNavController().navigate(R.id.zapatosFragment)
        }

        view.findViewById<CardView>(R.id.cv_accesories_category).setOnClickListener {
            findNavController().navigate(R.id.accesoriosFragment)
        }

        return view
    }
}
