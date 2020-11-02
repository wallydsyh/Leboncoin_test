package com.leboncoin.test.wallyd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.*
import com.leboncoin.test.wallyd.model.AlbumDataBase
import com.leboncoin.test.wallyd.model.AlbumSeparatorModel
import com.leboncoin.test.wallyd.model.AlbumsModel
import com.leboncoin.test.wallyd.model.MyPagingSource
import com.leboncoin.test.wallyd.repository.AlbumsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(private val repository: AlbumsRepository, app: Application) :
    AndroidViewModel(app) {
    private val parentJob = Job()
    private val albumDao = AlbumDataBase.getDatabase(app).albumDao()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    private val scope = CoroutineScope(coroutineContext)

    fun fetchAlbumsFromDB(): Flow<PagingData<AlbumsModel>> {
        return Pager(PagingConfig(pageSize = 5, enablePlaceholders = false, maxSize = 200)) {
            MyPagingSource(repository, albumDao)
        }.flow.cachedIn(scope)
    }
/*
    val albums: Flow<PagingData<AlbumSeparatorModel>> = fetchAlbumsFromDB()
        .map { pagingData ->
            pagingData.map {
                AlbumSeparatorModel.AlbumsItem(it)
            }
        }
        .map {
            it.insertSeparators { before, after ->

                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators null
                }

                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators null
                }

                // check between 2 items
                if (before.albumsModel?.albumId!! > after.albumsModel?.albumId!!) {

                    if (after.albumsModel.albumId >= 1) {
                        AlbumSeparatorModel.SeparatorModelItem("Album ${before.albumsModel.albumId}")
                    } else {
                        AlbumSeparatorModel.SeparatorModelItem("Less than 1000 vote count")
                    }
                } else {
                    // no separator
                    null
                }
            }
        }

 */

    fun cancelAllRequest() {
        coroutineContext.cancel()
    }

}