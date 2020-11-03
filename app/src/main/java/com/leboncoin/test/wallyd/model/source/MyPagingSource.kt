package com.leboncoin.test.wallyd.model.source

import androidx.paging.PagingSource
import com.leboncoin.test.wallyd.model.AlbumDao
import com.leboncoin.test.wallyd.model.AlbumsModel
import com.leboncoin.test.wallyd.repository.AlbumsRepository

class MyPagingSource(
    private val repository: AlbumsRepository,
    private val albumDao: AlbumDao
) : PagingSource<Int, AlbumsModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumsModel> {
        return try {
            val nextPageNumber = params.key  ?: 1
            if (isDatabaseEmpty()) {
                albumDao.insert(repository.getAlbumsList())
            }
            val data = albumDao.allAlbums(nextPageNumber)
            LoadResult.Page(
                data = data,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1, // Only paging forward.
                nextKey = nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    private suspend fun isDatabaseEmpty(): Boolean {
        return albumDao.isAlbumsExist().isEmpty()
    }
}