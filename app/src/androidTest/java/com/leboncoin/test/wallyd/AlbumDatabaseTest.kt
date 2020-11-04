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
import java.util.ArrayList

@RunWith(AndroidJUnit4::class)
class AlbumDatabaseTest {
    private lateinit var albumDao: AlbumDao
    private lateinit var dataBase: AlbumDataBase
    private val albumsModel = AlbumsModel(1, 1, "new Album", "url", "thumbnailUrl")
    private var albumList = ArrayList<AlbumsModel>()


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
    fun writeAndReadAlbum() {
        albumDao.insert(albumsModel)
        val albumModel = albumDao.getAlbumByAlbumId(1)
        assertThat(albumsModel, equalTo(albumModel))
    }


    @Test
    fun addAlbumInDatabase() {
        albumList.add(albumsModel)
        albumDao.insert(albumList)
        assertThat(albumDao.getAlbumByAlbumId(albumsModel.albumId), equalTo(albumsModel))
    }

    @Test
    fun checkIfAlbumContains50songs() {
      //  albumList = albumDao.getAllAlbumsByAlbumId(1)
        assertThat(albumList.size, equalTo(50))
    }
}