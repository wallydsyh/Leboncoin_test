package com.leboncoin.test.wallyd.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leboncoin.test.wallyd.model.AlbumsModel
import com.leboncoin.test.wallyd.repository.AlbumsRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(private val repository: AlbumsRepository) : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO


    private val scope = CoroutineScope(coroutineContext)
    val albumsList = MutableLiveData<List<AlbumsModel>>()

    fun fetchAlbums() {
        scope.launch {
            val albums = repository.getAlbumsList()
            albumsList.postValue(albums)
        }
    }

    fun cancelAllRequest() {
        coroutineContext.cancel()
    }
}