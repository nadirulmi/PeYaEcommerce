package com.example.peyaecommerce.model.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val nombre: String,
    val categoria: String,
    val precio: Double,
    val imagenResId: Int,
    val cantidad: Int
)