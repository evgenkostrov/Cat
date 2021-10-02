package com.task5.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.task5.model.CatsResponse

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(catEntity: List<CatsResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatItem(catItem: CatsResponse)

    @Query("SELECT * FROM catsresponse")
    fun getCatData(): PagingSource<Int, CatsResponse>

    @Query("DELETE FROM catsresponse")
    suspend fun clearAllCats()

    @Delete
    suspend fun deleteCatItem(catItem: CatsResponse)

}
