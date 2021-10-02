package com.task5.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.task5.constants.AppConstants.DEFAULT_PAGE_SIZE
import com.task5.datasource.CatMediator
import com.task5.datasource.NetworkDataSource
import com.task5.db.CatDatabase
import com.task5.model.CatsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



class CatDbRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val database: CatDatabase
) : CatRepository {


    /**
     * let's define page size, page size is the only required param, rest is optional
     */
    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }


    @ExperimentalPagingApi
    override fun getCats(): Flow<PagingData<CatsResponse>> {
        val pagingConfig: PagingConfig = getDefaultPageConfig()
        val pagingSourceFactory = { database.getCatDao().getCatData() }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = CatMediator(networkDataSource, database)
        ).flow
    }

}
