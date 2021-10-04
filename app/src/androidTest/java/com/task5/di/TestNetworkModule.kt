package com.task5.di

import com.task5.datasource.NetworkDataSource
import com.task5.datasource.NetworkDataSourceImpl
import com.task5.network.CatRetrofitService
import com.task5.repository.FakeCatRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object  TestNetworkModule {

    @Provides
    @Named("fake_api")
    fun provideRetrofitService(
    ): CatRetrofitService {
        return FakeCatRetrofitService()
    }


    @Provides
    @Named("fake_network")
    fun provideNetworkDataSource(
        @Named("fake_api") catRetrofitService: CatRetrofitService
    ): NetworkDataSource {
        return NetworkDataSourceImpl(catRetrofitService)
    }

}