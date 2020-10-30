package com.leboncoin.test.wallyd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leboncoin.test.wallyd.api.ApiHelper
import com.leboncoin.test.wallyd.api.ApiServiceImpl
import com.leboncoin.test.wallyd.viewModel.MainActivityViewModel
import com.leboncoin.test.wallyd.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var  mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setUpObserver()
    }


    fun setupViewModel() {
        mainActivityViewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(MainActivityViewModel::class.java)
    }
    fun setUpObserver() {
        mainActivityViewModel.fetchAlbums()
        mainActivityViewModel.albumsList.observe(this, Observer {
            Log.d("WallydSyh", it.toString())

        })

    }
}