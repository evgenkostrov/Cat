package com.task5.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task5.model.CatsResponse
import com.task5.model.RemoteKeys
import com.task5.db.dao.CatDao
import com.task5.db.dao.RemoteKeysDao

@Database(version = 1,entities = [CatsResponse::class, RemoteKeys::class ], exportSchema = false)
abstract class CatDatabase: RoomDatabase() {

    abstract fun getCatDao(): CatDao
    abstract fun getRepoDao(): RemoteKeysDao

    companion object{
        val DATABASE_NAME: String = "cats.db"
    }


}