package com.leboncoin.test.wallyd.repository

import com.leboncoin.test.wallyd.api.ApiHelper
import com.leboncoin.test.wallyd.model.AlbumsModel
import com.leboncoin.test.wallyd.model.AlbumsModelResponse

class AlbumsRepository(private val apiHelper: ApiHelper) : BaseRepository() {

    suspend fun getAlbumsList(): List<AlbumsModel>? {

        val albumResponse = safeApiCall({ apiHelper.getAlbums().await() }, "Error fetching Album ")

        return albumResponse?.results?.toMutableList()
    }
}