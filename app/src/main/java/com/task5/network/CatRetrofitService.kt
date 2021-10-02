package com.task5.network

import com.task5.model.CatsResponse


interface CatRetrofitService {

    suspend fun getCat(limit: Int,
                    page: Int,mimeType: String): List<CatsResponse>
}