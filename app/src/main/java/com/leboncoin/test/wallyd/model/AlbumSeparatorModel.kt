package com.leboncoin.test.wallyd.model

sealed class AlbumSeparatorModel {
    data class AlbumsItem(val albumsModel: AlbumsModel?): AlbumSeparatorModel()
    data class SeparatorModelItem(val description: String) : AlbumSeparatorModel()
}