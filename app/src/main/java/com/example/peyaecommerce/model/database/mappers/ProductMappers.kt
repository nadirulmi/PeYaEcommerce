package com.example.peyaecommerce.model.database.mappers

import com.example.peyaecommerce.model.data.remote.FoodDto
import com.example.peyaecommerce.model.database.entities.ProductEntity
import com.example.peyaecommerce.model.models.Product

fun FoodDto.toEntity() = ProductEntity(
    _id = this._id,
    name = this.name,
    description = this.description,
    imageUrl = this.imageUrl,
    price = this.price,
    category = determinarCategoria(this.name, this.description),
    hasDrink = this.hasDrink,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

fun FoodDto.toProduct(): Product {
    return Product(
        id = _id,
        name = name,
        price = price.toDouble(),
        imageResId = 0,
        category = determinarCategoria(name, description),
        imageUrl = imageUrl,
        hasDrink = hasDrink,
        description = description
    )
}

private fun determinarCategoria(nombre: String, descripcion: String): String {
    val text = (nombre + descripcion).lowercase()

    return when {
        "pizza" in text -> "Pizza"
        "pollo" in text || "chicken" in text -> "Pollo"
        "carne" in text || "res" in text || "pastor" in text || "beef" in text -> "Carne"
        "pescado" in text || "sushi" in text || "salmon" in text || "atun" in text -> "Pescado"
        "ensalada" in text || "salad" in text -> "Ensaladas"
        else -> "Otros"
    }
}
