package com.example.peyaecommerce.model.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("foods")
    suspend fun getFoods(): Response<List<FoodDto>>
}