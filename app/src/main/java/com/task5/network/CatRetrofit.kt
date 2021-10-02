package com.task5.network

import com.task5.model.CatsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CatRetrofit {


    @GET("images/search?")
    suspend fun getCats(@Query("limit") limit: Int,
                       @Query("page") page: Int,
                       @Query("mime_types") mime_types: String? = null): List<CatsResponse>
}