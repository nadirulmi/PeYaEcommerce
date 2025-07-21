package com.example.peyaecommerce.model.data.remote

data class UserDto(
    val _id: String?,
    val fullName: String,
    val email: String,
    val nationality: String
)

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val encryptedPassword: String,
    val nationality: String
)

data class LoginRequest(
    val email: String,
    val encryptedPassword: String
)

data class LoginResponse(
    val message: String,
    val user: UserDto
)
