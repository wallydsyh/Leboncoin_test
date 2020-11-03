package com.leboncoin.test.wallyd.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDao {
    @Query("SELECT * FROM AlbumsModel WHERE albumId = :albumId")
    fun allAlbums(albumId: Int): List<AlbumsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(albums: List<AlbumsModel>)

    @Insert
     fun insert(album: AlbumsModel)

    @Query("SELECT * FROM AlbumsModel ORDER BY id LIMIT 1")
    suspend fun isAlbumsExist(): List<AlbumsModel>

    @Query("SELECT * FROM AlbumsModel WHERE albumId = :albumId")
     fun getAlbum(albumId: Int): AlbumsModel

}