package com.leboncoin.test.wallyd.model

import androidx.paging.PagingSource
import com.leboncoin.test.wallyd.repository.AlbumsRepository

class MyPagingSource(
    private val repository: AlbumsRepository,
    private val albumDao: AlbumDao
) : PagingSource<Int, AlbumsModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumsModel> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: 1
        if (isDatabaseEmpty()) {
            albumDao.insert(repository.getAlbumsList())
        }
        val data = albumDao.allAlbums(nextPageNumber)
        return LoadResult.Page(
            data = data,
            prevKey = null, // Only paging forward.
            nextKey = nextPageNumber.plus(1)
        )
    }

    private suspend fun isDatabaseEmpty(): Boolean {
        return albumDao.checkAlbum().isEmpty()
    }
}
