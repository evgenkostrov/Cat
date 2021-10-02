package com.task5.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.task5.model.CatsResponse
import com.task5.repository.CatDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class CatsViewModel
@Inject
constructor( private val repository: CatDbRepository
): ViewModel() {


    fun fetchCatData(): Flow<PagingData<CatsResponse>> {
        return repository.getCats().cachedIn(viewModelScope)
    }


}