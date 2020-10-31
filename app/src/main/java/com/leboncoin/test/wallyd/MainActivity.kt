package com.leboncoin.test.wallyd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.test.wallyd.adapter.AlbumsAdapter
import com.leboncoin.test.wallyd.api.ApiHelper
import com.leboncoin.test.wallyd.api.ApiServiceImpl
import com.leboncoin.test.wallyd.databinding.ActivityMainBinding
import com.leboncoin.test.wallyd.viewModel.MainActivityViewModel
import com.leboncoin.test.wallyd.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var albumsAdapter: AlbumsAdapter = AlbumsAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpAdapter()
        setupViewModel()
        setUpObserver()

    }

    private fun setUpAdapter() {
        recyclerView = binding.recyclerView
        recyclerView.adapter = albumsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupViewModel() {
        mainActivityViewModel =
            ViewModelProvider(this, ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(
                MainActivityViewModel::class.java
            )
    }

    private fun setUpObserver() {
        mainActivityViewModel.fetchAlbums()
        mainActivityViewModel.albumsList.observe(this, Observer {
            albumsAdapter.setAlbumsData(it)
        })
    }
}