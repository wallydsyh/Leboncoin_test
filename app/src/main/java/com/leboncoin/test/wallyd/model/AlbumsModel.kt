package com.leboncoin.test.wallyd.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumsModel(
    val albumId: Int,
    @PrimaryKey val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
