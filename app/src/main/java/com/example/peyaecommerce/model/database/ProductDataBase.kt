package com.example.peyaecommerce.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.peyaecommerce.model.database.dao.CartDao
import com.example.peyaecommerce.model.database.dao.OrderDao
import com.example.peyaecommerce.model.database.dao.ProductDao
import com.example.peyaecommerce.model.database.entities.CartItemEntity
import com.example.peyaecommerce.model.database.entities.OrderEntity
import com.example.peyaecommerce.model.database.entities.OrderItemEntity
import com.example.peyaecommerce.model.database.entities.ProductEntity

@Database(
    entities = [ProductEntity::class, CartItemEntity::class, OrderEntity::class, OrderItemEntity::class],
    version = 9,
    exportSchema = false
)
abstract class ProductDataBase : RoomDatabase() {

    abstract fun itemDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var Instance: ProductDataBase? = null

        private val MIGRATION_8_9 = object : Migration(8, 9) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE products ADD COLUMN imageUrl TEXT")
            }
        }

        fun getDatabase(context: Context): ProductDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ProductDataBase::class.java,
                    "item_database"
                )
                    .addMigrations(MIGRATION_8_9)
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}