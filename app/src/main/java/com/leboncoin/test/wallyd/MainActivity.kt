package com.leboncoin.test.wallyd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.leboncoin.test.wallyd.adapter.AlbumsAdapter
import com.leboncoin.test.wallyd.adapter.AlbumsLoadStateAdapter
import com.leboncoin.test.wallyd.api.ApiHelper
import com.leboncoin.test.wallyd.api.ApiServiceImpl
import com.leboncoin.test.wallyd.databinding.ActivityMainBinding
import com.leboncoin.test.wallyd.viewModel.MainActivityViewModel
import com.leboncoin.test.wallyd.viewModel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var albumsAdapter: AlbumsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        setUpAdapter()
        setUpObserver()
    }

    private fun setUpAdapter() {
        albumsAdapter = AlbumsAdapter()
        albumsAdapter.withLoadStateFooter(
            AlbumsLoadStateAdapter {
                albumsAdapter.retry()
            }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = albumsAdapter
        }
    }

    private fun setupViewModel() {
        mainActivityViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(ApiHelper(ApiServiceImpl()), this.application)
            ).get(
                MainActivityViewModel::class.java
            )
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            mainActivityViewModel.allAlbums.collectLatest {
                albumsAdapter.submitData(it)
            }
        }
    }
}