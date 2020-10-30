package com.leboncoin.test.wallyd.api

import com.leboncoin.test.wallyd.model.AlbumsModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("technical-test.json")
     fun getAlbums(): Deferred<Response<AlbumsModelResponse>>
}