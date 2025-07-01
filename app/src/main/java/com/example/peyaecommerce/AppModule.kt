package com.example.peyaecommerce

import com.example.peyaecommerce.model.data.ProfileData
import com.example.peyaecommerce.model.data.ProfileDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideProfileDataSource(): ProfileDataSource {
        return ProfileData()
    }
}