package com.leboncoin.test.wallyd.api

import com.leboncoin.test.wallyd.model.AlbumsModel
import retrofit2.http.GET

interface ApiService {
    @GET("technical-test.json")
    suspend fun getAlbums(): ArrayList<AlbumsModel>
}