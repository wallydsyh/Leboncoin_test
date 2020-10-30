package com.leboncoin.test.wallyd.utils

import com.google.gson.Gson
import com.leboncoin.test.wallyd.model.AlbumsModel


class TypeConverter {
    @androidx.room.TypeConverter
    fun fromString(value: String?): AlbumsModel {
        return Gson().fromJson(value, AlbumsModel::class.java)
    }

    @androidx.room.TypeConverter
    fun fromProductModel(albumsModel: AlbumsModel): String? {
        return Gson().toJson(albumsModel)
    }
}