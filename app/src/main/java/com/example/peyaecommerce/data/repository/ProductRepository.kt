package com.example.peyaecommerce.data.repository

import com.example.peyaecommerce.R
import com.example.peyaecommerce.data.model.Product

object ProductRepository {
    val products = listOf(
        Product(
            nombre = "Hamburguesa Clásica",
            precio = 1500.0,
            imagenResId = R.drawable.burger,
            categoria = "Comida"
        ),
        Product(
            nombre = "Pizza Mozzarella",
            precio = 2000.0,
            imagenResId = R.drawable.pizza,
            categoria = "Comida"
        ),
        Product(
            nombre = "Empanadas de Carne",
            precio = 1800.0,
            imagenResId = R.drawable.empanadas,
            categoria = "Comida"
        ),
        Product(
            nombre = "Ensalada Fresca",
            precio = 1300.0,
            imagenResId = R.drawable.salad,
            categoria = "Comida"
        ),
        Product(
            nombre = "Sándwich de Pollo",
            precio = 1700.0,
            imagenResId = R.drawable.sandwich,
            categoria = "Comida"
        )
    )
}

