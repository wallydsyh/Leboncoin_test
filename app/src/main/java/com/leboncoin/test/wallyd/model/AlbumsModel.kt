package com.leboncoin.test.wallyd.model

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.leboncoin.test.wallyd.utils.TypeConverter

data class AlbumsModel(
    val albumId: Int,

    val id: Int,

    val title: String,

    val url: String,

    val thumbnailUrl: String
)
