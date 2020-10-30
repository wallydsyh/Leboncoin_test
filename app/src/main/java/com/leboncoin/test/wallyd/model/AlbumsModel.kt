package com.leboncoin.test.wallyd.model

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.leboncoin.test.wallyd.utils.TypeConverter

data class AlbumsModel(
    @SerializedName("albumId")
    val albumId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)
