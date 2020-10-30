package com.leboncoin.test.wallyd.model

import androidx.room.TypeConverters
import com.google.gson.Gson
import com.leboncoin.test.wallyd.utils.TypeConverter
data class AlbumsModelResponse(
    val results: List<AlbumsModel>
)
