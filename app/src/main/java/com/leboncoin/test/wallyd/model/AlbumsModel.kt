package com.leboncoin.test.wallyd.model

import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig

data class AlbumsModel(
    val albumId: Int,

    val id: Int,

    val title: String,

    val url: String,

    val thumbnailUrl: String
)
