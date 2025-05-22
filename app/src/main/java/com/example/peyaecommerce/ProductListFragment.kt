package com.example.peyaecommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProductListFragment : Fragment() {

    private val fakeProducts = listOf(
        "Leche Entera - $1.50",
        "Pan Baguette - $2.00",
        "Queso Cremoso - $3.20",
        "Café Molido - $4.50",
        "Arroz Integral - $2.30",
        "Huevos x12 - $2.80"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment, container, false)

        val containerLayout = view.findViewById<LinearLayout>(R.id.productsContainer)

        for (product in fakeProducts) {
            val textView = TextView(requireContext())
            textView.text = product
            textView.textSize = 18f
            textView.setPadding(8, 16, 8, 16)
            containerLayout.addView(textView)
        }

        return view
    }
}
