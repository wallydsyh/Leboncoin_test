package com.leboncoin.test.wallyd.model

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDao {
    @Query("SELECT * FROM AlbumsModel")
    fun allAlbums(): PagingSource<Int, AlbumsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(albums: List<AlbumsModel>)

    @Insert
    suspend fun insert(album: AlbumsModel)

    @Query("SELECT * FROM AlbumsModel ORDER BY id LIMIT 1")
    suspend fun checkAlbum(): List<AlbumsModel>

}