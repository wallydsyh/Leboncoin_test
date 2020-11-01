package com.leboncoin.test.wallyd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.leboncoin.test.wallyd.model.AlbumDataBase
import com.leboncoin.test.wallyd.model.AlbumsModel
import com.leboncoin.test.wallyd.repository.AlbumsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(private val repository: AlbumsRepository, app: Application) :
    AndroidViewModel(app) {
    private val parentJob = Job()
    private val albumDao = AlbumDataBase.getDatabase(app).albumDao()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    private val scope = CoroutineScope(coroutineContext)

    fun fetchAlbums(): Flow<PagingData<AlbumsModel>> {
        return Pager(PagingConfig(pageSize = 10, enablePlaceholders = false, maxSize = 200)) {
            albumDao.allAlbums()
        }.flow.cachedIn(scope)
    }


    private suspend fun getAlbums(): List<AlbumsModel> {
        return repository.getAlbumsList(0)
    }
    suspend fun checkAlbum(): Boolean {
        return albumDao.checkAlbum().isNotEmpty()
    }

    suspend fun insertAlbums() {
        albumDao.insert(getAlbums())
    }


    fun cancelAllRequest() {
        coroutineContext.cancel()
    }

}