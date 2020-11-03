package com.leboncoin.test.wallyd.repository

import com.leboncoin.test.wallyd.api.ApiHelper

class AlbumsRepository(private val apiHelper: ApiHelper){
    suspend fun getAlbumsList() = apiHelper.getAlbums()
}