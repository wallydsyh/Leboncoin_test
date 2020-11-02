package com.leboncoin.test.wallyd.api

import com.leboncoin.test.wallyd.model.AlbumsModel

class ApiServiceImpl(
    private val apiService: ApiService = ApiClient().getClient().create(ApiService::class.java)
) : ApiService {
    override suspend fun getAlbums(): ArrayList<AlbumsModel> {
        return apiService.getAlbums()
    }

}