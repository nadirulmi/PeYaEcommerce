package com.example.peyaecommerce.model.database.mappers

import com.example.peyaecommerce.model.models.CartItem
import com.example.peyaecommerce.model.database.entities.CartItemEntity

fun CartItem.toEntity(): CartItemEntity = CartItemEntity(
    productId = product.id ?: "",
    nombre = product.name,
    categoria = product.category,
    precio = product.price,
    imagenResId = product.imageResId,
    imageUrl = product.imageUrl,
    cantidad = quantity
)