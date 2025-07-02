package com.example.peyaecommerce.model.database.mappers

import com.example.peyaecommerce.model.models.CartItem
import com.example.peyaecommerce.model.database.entities.CartItemEntity

fun CartItem.toEntity(): CartItemEntity = CartItemEntity(
    productId = product.id ?: 0,
    nombre = product.nombre,
    categoria = product.categoria,
    precio = product.precio,
    imagenResId = product.imagenResId,
    cantidad = quantity
)