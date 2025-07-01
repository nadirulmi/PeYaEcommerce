package com.example.peyaecommerce.model.repository

import com.example.peyaecommerce.R
import com.example.peyaecommerce.model.Product

object ProductRepository {
    val products = listOf(
        Product(
            id = null,
            nombre = "Hamburguesa Clásica",
            precio = 1500.0,
            imagenResId = R.drawable.burger,
            categoria = "Comida"
        ),
        Product(
            id = null,
            nombre = "Pizza Mozzarella",
            precio = 2000.0,
            imagenResId = R.drawable.pizza,
            categoria = "Comida"
        ),
        Product(
            id = null,
            nombre = "Empanadas de Carne",
            precio = 1800.0,
            imagenResId = R.drawable.empanadas,
            categoria = "Comida"
        ),
        Product(
            id = null,
            nombre = "Ensalada Fresca",
            precio = 1300.0,
            imagenResId = R.drawable.salad,
            categoria = "Comida"
        ),
        Product(
            id = null,
            nombre = "Sándwich de Pollo",
            precio = 1700.0,
            imagenResId = R.drawable.sandwich,
            categoria = "Comida"
        )
    )
}

