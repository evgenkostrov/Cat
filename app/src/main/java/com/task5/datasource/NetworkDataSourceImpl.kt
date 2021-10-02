package com.task5.datasource

import com.task5.model.CatsResponse
import com.task5.network.CatRetrofitService

class NetworkDataSourceImpl
constructor(
    private val catRetrofitService: CatRetrofitService,
): NetworkDataSource {

    override suspend fun getCat(limit: Int, page: Int, mimeType: String): List<CatsResponse> {
        return catRetrofitService.getCat(limit, page, mimeType)
    }

}