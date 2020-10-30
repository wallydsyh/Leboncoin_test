package com.leboncoin.test.wallyd.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leboncoin.test.wallyd.api.ApiHelper
import com.leboncoin.test.wallyd.api.ApiService
import com.leboncoin.test.wallyd.repository.AlbumsRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> {
                return MainActivityViewModel(
                    AlbumsRepository(
                        apiHelper
                    )
                ) as T

            }
            else -> throw IllegalArgumentException("Unknown class name")

        }
    }

}