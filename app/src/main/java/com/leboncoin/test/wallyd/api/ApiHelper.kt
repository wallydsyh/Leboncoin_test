package com.leboncoin.test.wallyd.api


class ApiHelper(private val apiService: ApiService) {
        suspend fun getAlbums()= apiService.getAlbums()
}