package com.task5

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.task5.db.CatDatabase
import com.task5.repository.CatDbRepository
import com.task5.ui.adapter.CatListAdapter
import com.task5.ui.viewmodel.CatsViewModel
import com.task5.utils.GlideApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@HiltAndroidTest
class CatUnitTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @ExperimentalPagingApi
    @Named("test_db")
    @Inject lateinit var catDatabase: CatDatabase

    @ExperimentalPagingApi
    @Named("test_repo")
    @Inject lateinit var catDbRepository: CatDbRepository




    @ExperimentalPagingApi
    @Inject
    lateinit var context: Application

    @ExperimentalCoroutinesApi
    private lateinit var viewModel: CatsViewModel



    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    @InternalCoroutinesApi
    @Test
    fun getCatData() = runBlocking {

        val glide = GlideApp.with(context)
        val adapter = CatListAdapter(glide)

        val job = launch {
            viewModel.fetchCatData().distinctUntilChanged().collectLatest {
                println(it)
                    adapter.submitData(it)

            }
        }
        assertFalse(adapter.itemCount>0)
        job.cancel()


    }



    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    @Before
    fun init() {
        hiltRule.inject()
        viewModel = CatsViewModel(catDbRepository)
    }



}