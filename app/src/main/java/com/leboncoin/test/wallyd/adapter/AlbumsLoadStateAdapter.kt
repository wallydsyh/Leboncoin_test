package com.leboncoin.test.wallyd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.test.wallyd.R
import com.leboncoin.test.wallyd.databinding.LoadStateViewBinding

class AlbumsLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<AlbumsLoadStateAdapter.LoadStateViewHolder>() {
    private lateinit var binding: LoadStateViewBinding

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        val progress = binding.loadingIndicator
        val buttonRetry = binding.buttonRetry
        val txtErrorMessage = binding.textViewErrorMessage

        when (loadState) {
            is LoadState.Loading -> {
                // show progress view
                buttonRetry.visibility = View.GONE
                progress.visibility = View.VISIBLE
                txtErrorMessage.visibility = View.GONE
            }
            is LoadState.Error -> {
                buttonRetry.visibility = View.VISIBLE
                txtErrorMessage.visibility = View.VISIBLE
                progress.visibility = View.GONE
                txtErrorMessage.text = loadState.error.localizedMessage
            }
            else -> {
                progress.visibility = View.GONE
            }
        }
        buttonRetry.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.load_state_view, parent, false)
        return LoadStateViewHolder(
            binding.root
        )
    }
    class LoadStateViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
