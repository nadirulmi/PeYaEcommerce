package com.example.peyaecommerce.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String = "",
    val categoria: String = "",
    val precio: Double = 0.0,
    val imagenResId: Int = 0
)