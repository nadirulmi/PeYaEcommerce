package com.example.peyaecommerce.model.models

data class Product(
    val id: Int? = 0,
    val nombre: String,
    val precio: Double,
    val imagenResId: Int,
    val categoria: String
) {
    val destacado: Boolean
        get() = precio > 100


}
