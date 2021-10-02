package com.task5.datasource

import com.task5.model.CatsResponse

interface NetworkDataSource {

    suspend fun getCat( limit: Int,
                     page: Int,mimeType:String): List<CatsResponse>
}