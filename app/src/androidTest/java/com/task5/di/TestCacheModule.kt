package com.task5.di

import android.content.Context
import androidx.room.Room
import com.task5.db.CatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TestCacheModule {


    @Named("test_db")
    @Provides
    fun provideCatDb(@ApplicationContext context: Context): CatDatabase {
        return Room
            .databaseBuilder(
                context,
                CatDatabase::class.java,
                CatDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}