package com.leboncoin.test.wallyd.api

import com.leboncoin.test.wallyd.model.AlbumsModel
import com.leboncoin.test.wallyd.model.AlbumsModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("technical-test.json")
     suspend fun getAlbums(): List<AlbumsModel>
}