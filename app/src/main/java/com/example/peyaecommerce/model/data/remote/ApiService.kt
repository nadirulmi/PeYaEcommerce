package com.example.peyaecommerce.model.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("foods")
    suspend fun getFoods(): Response<List<FoodDto>>

    //Usuarios
    @POST("users/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<UserDto>

    @POST("users/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>

}