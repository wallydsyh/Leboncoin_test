package com.leboncoin.test.wallyd

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.leboncoin.test.wallyd.model.AlbumDao
import com.leboncoin.test.wallyd.model.AlbumDataBase
import com.leboncoin.test.wallyd.model.AlbumsModel
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AlbumDatabaseTest {
    private var albumDao: AlbumDao? = null
    private lateinit var dataBase: AlbumDataBase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataBase = AlbumDataBase.getDatabase(context)
        dataBase = Room.inMemoryDatabaseBuilder(
            context, AlbumDataBase::class.java
        ).build()
        albumDao = dataBase.albumDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        dataBase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadAlbums() {
        val albumsModel = AlbumsModel(1, 1, "new Album", "url", "thumbnailUrl")
        albumDao?.insert(albumsModel)
        val byId = albumDao?.getAlbum(1)
        assertThat(albumsModel, equalTo(byId))

    }
}