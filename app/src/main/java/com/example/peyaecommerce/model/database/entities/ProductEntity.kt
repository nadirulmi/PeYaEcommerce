package com.example.peyaecommerce.model.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey
    val _id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Int,
    val category: String,
    val hasDrink: Boolean,
    val createdAt: String,
    val updatedAt: String
)