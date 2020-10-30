package com.leboncoin.test.wallyd.api


class ApiHelper(private val apiService: ApiService) {
     fun getAlbums()= apiService.getAlbums()
}