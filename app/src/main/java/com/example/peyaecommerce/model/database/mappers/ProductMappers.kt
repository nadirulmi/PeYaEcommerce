package com.example.peyaecommerce.model.database.mappers

import com.example.peyaecommerce.model.models.Product
import com.example.peyaecommerce.model.database.entities.ProductEntity

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id ?: 0,
        nombre = this.nombre,
        categoria = this.categoria,
        precio = this.precio,
        imagenResId = this.imagenResId
    )
}

fun ProductEntity.toProduct(): Product {
    return Product(
        id = this.id,
        nombre = this.nombre,
        categoria = this.categoria,
        precio = this.precio,
        imagenResId = this.imagenResId
    )
}