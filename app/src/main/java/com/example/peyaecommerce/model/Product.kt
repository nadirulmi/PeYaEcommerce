package com.example.peyaecommerce.model

data class Product(
    val nombre: String,
    val precio: Double,
    val imagenResId: Int,
    val categoria: String
) {
    val destacado: Boolean
        get() = precio > 100


}
