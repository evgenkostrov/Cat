package com.task5.di

import androidx.paging.ExperimentalPagingApi
import com.task5.datasource.NetworkDataSource
import com.task5.db.CatDatabase
import com.task5.repository.CatDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TestInteractorsModule {

    @ExperimentalPagingApi
    @Named("test_repo")
    @Provides
    fun provideRepository(
        cacheDataSource: CatDatabase,
        @Named("fake_network") networkDataSource: NetworkDataSource
    ): CatDbRepository {
        return CatDbRepository(networkDataSource, cacheDataSource)
    }
}