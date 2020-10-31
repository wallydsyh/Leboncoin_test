package com.leboncoin.test.wallyd.repository

import com.leboncoin.test.wallyd.api.ApiHelper

class AlbumsRepository(private val apiHelper: ApiHelper) : BaseRepository() {

     suspend fun getAlbumsList(nextPageNumber: Int) = apiHelper.getAlbums(nextPageNumber)

}