package com.example.peyaecommerce.model

import com.example.peyaecommerce.model.Product

data class CartItem(
    val product: Product,
    var quantity: Int
)