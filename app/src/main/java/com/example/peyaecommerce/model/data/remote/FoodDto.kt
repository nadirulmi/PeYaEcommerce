package com.example.peyaecommerce.model.data.remote

data class FoodDto(
    val _id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Int,
    val hasDrink: Boolean,
    val createdAt: String,
    val updatedAt: String
)