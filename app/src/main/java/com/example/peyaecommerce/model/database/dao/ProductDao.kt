package com.example.peyaecommerce.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.peyaecommerce.model.database.entities.ProductEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao{

    @Insert
    suspend fun insert(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    fun getAllItems(): Flow<List<ProductEntity>>

}