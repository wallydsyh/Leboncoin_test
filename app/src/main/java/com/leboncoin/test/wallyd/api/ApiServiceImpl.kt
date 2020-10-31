package com.leboncoin.test.wallyd.api

import com.leboncoin.test.wallyd.model.AlbumsModel
import com.leboncoin.test.wallyd.model.AlbumsModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class ApiServiceImpl(
    private val apiService: ApiService = ApiClient().getClient().create(ApiService::class.java)
) : ApiService {
    override suspend fun getAlbums(): List<AlbumsModel> {
        return apiService.getAlbums()
    }

}