package com.leboncoin.test.wallyd.viewModel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.leboncoin.test.wallyd.model.AlbumsModel
import com.leboncoin.test.wallyd.model.MyPagingSource
import com.leboncoin.test.wallyd.repository.AlbumsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(private val repository: AlbumsRepository) : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO


    private val scope = CoroutineScope(coroutineContext)
    fun fetchAlbums(): Flow<PagingData<AlbumsModel>> {
        return Pager(PagingConfig(pageSize = 60, enablePlaceholders = true, maxSize = 200)) {
            MyPagingSource(repository, "")
        }.flow.cachedIn(scope)
    }


    fun cancelAllRequest() {
        coroutineContext.cancel()
    }


}