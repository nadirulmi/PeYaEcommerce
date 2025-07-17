package com.example.peyaecommerce.model.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: String,
    val nombre: String,
    val categoria: String,
    val precio: Double,
    val imagenResId: Int,
    val imageUrl: String?,
    val cantidad: Int
)