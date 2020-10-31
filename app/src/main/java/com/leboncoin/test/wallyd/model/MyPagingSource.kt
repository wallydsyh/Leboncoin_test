package com.leboncoin.test.wallyd.model

import androidx.paging.PagingSource
import com.leboncoin.test.wallyd.repository.AlbumsRepository

class MyPagingSource(
    val repository: AlbumsRepository,
    val query: String
) : PagingSource<Int, AlbumsModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumsModel> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: 1
        val response = repository.getAlbumsList(nextPageNumber)
        return LoadResult.Page(
            data = response,
            prevKey = null, // Only paging forward.
            nextKey = response.lastOrNull()?.id
        )

    }
    override val keyReuseSupported: Boolean = true
}
