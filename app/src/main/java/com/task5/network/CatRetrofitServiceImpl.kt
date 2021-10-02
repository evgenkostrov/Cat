package com.task5.network

import com.task5.model.CatsResponse
import com.task5.network.CatRetrofit
import com.task5.network.CatRetrofitService


class CatRetrofitServiceImpl constructor(
    private val catRetrofit: CatRetrofit
): CatRetrofitService {

    override suspend fun getCat(limit: Int, page: Int, mimeType: String): List<CatsResponse> {
        return catRetrofit.getCats(limit,page,mimeType)
    }

}