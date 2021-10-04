package com.task5.repository

import com.catpaging.ui.activity.CatFactoryTest
import com.task5.model.CatsResponse
import com.task5.network.CatRetrofitService


class FakeCatRetrofitService : CatRetrofitService {

    override suspend fun getCat(limit: Int, page: Int, mimeType: String): List<CatsResponse> {
        val factory = CatFactoryTest()
        return listOf(factory.createCatResponse(),factory.createCatResponse(),factory.createCatResponse())

    }

}