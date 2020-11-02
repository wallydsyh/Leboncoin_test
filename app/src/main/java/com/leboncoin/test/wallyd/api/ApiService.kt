package com.leboncoin.test.wallyd.api

import com.leboncoin.test.wallyd.model.AlbumsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("technical-test.json")
    suspend fun getAlbums(): ArrayList<AlbumsModel>
}