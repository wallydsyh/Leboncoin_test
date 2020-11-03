package com.leboncoin.test.wallyd.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leboncoin.test.wallyd.api.ApiHelper
import com.leboncoin.test.wallyd.repository.AlbumsRepository

class ViewModelFactory(private val apiHelper: ApiHelper, private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> {
                return MainActivityViewModel(AlbumsRepository(apiHelper),application) as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }

}