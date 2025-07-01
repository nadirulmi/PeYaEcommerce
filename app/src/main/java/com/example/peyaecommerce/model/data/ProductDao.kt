package com.example.peyaecommerce.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao{

    @Insert
    suspend fun insert(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    fun getAllItems(): Flow<List<ProductEntity>>

}