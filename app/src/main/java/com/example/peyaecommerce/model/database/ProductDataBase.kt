package com.example.peyaecommerce.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.peyaecommerce.model.database.dao.CartDao
import com.example.peyaecommerce.model.database.dao.OrderDao
import com.example.peyaecommerce.model.database.dao.ProductDao
import com.example.peyaecommerce.model.database.entities.CartItemEntity
import com.example.peyaecommerce.model.database.entities.OrderEntity
import com.example.peyaecommerce.model.database.entities.OrderItemEntity
import com.example.peyaecommerce.model.database.entities.ProductEntity

@Database(
    entities = [ProductEntity::class, CartItemEntity::class, OrderEntity::class, OrderItemEntity::class],
    version = 6,
    exportSchema = false
)
abstract class ProductDataBase: RoomDatabase() {

    abstract fun itemDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var Instance: ProductDataBase? = null

        fun getDatabase(context: Context): ProductDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ProductDataBase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build().also {
                    Instance = it
                }
            }
        }
    }

}