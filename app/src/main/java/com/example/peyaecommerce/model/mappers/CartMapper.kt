package com.example.peyaecommerce.model.mappers

import com.example.peyaecommerce.model.CartItem
import com.example.peyaecommerce.model.data.CartItemEntity

fun CartItem.toEntity(): CartItemEntity = CartItemEntity(
    productId = product.id ?: 0,
    nombre = product.nombre,
    categoria = product.categoria,
    precio = product.precio,
    imagenResId = product.imagenResId,
    cantidad = quantity
)