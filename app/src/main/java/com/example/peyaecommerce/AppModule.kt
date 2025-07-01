package com.example.peyaecommerce

import android.content.Context
import com.example.peyaecommerce.model.data.CartDao
import com.example.peyaecommerce.model.data.OrderDao
import com.example.peyaecommerce.model.data.ProductDao
import com.example.peyaecommerce.model.data.ProductDataBase
import com.example.peyaecommerce.model.data.ProfileData
import com.example.peyaecommerce.model.data.ProfileDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideProfileDataSource(): ProfileDataSource {
        return ProfileData()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ProductDataBase {
        return ProductDataBase.getDatabase(context)
    }

    @Provides
    fun provideProductDao(db: ProductDataBase): ProductDao {
        return db.itemDao()
    }

    @Provides
    fun provideCartDao(db: ProductDataBase): CartDao {
        return db.cartDao()
    }

    @Provides
    fun provideOrderDao(db: ProductDataBase): OrderDao {
        return db.orderDao()
    }
}