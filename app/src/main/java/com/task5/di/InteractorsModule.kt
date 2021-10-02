package com.example.mypaging.di

import com.task5.datasource.NetworkDataSource
import com.task5.db.CatDatabase
import com.task5.repository.CatDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 object InteractorsModule {


    @Singleton
    @Provides
    fun provideRepository(
        cacheDataSource: CatDatabase,
        networkDataSource: NetworkDataSource
    ): CatDbRepository {
        return CatDbRepository(networkDataSource, cacheDataSource)
    }
}