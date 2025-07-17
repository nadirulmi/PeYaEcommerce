package com.example.peyaecommerce.model.models

data class Product(
    val id: String? = null,
    val name: String,
    val price: Double,
    val imageResId: Int = 0,
    val category: String,
    val imageUrl: String? = null,
    val hasDrink: Boolean = false,
    val description: String? = null
)
