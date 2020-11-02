package com.leboncoin.test.wallyd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.test.wallyd.R
import com.leboncoin.test.wallyd.databinding.AlbumItemBinding
import com.leboncoin.test.wallyd.model.AlbumsModel
import com.squareup.picasso.Picasso

class AlbumsAdapter :
    PagingDataAdapter<AlbumsModel, RecyclerView.ViewHolder>(diffCallback) {
    private lateinit var binding: AlbumItemBinding

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.album_item, parent, false)

        return AlbumsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val albumSeparatorModel: AlbumsModel = getItem(position)!!
        albumSeparatorModel.let {
            binding.textViewAlbumTitle.text = it.title
            binding.textViewId.text = it.id.toString()
            Picasso.get().load(it.url)
                .into(binding.imageViewAlbumImage)
        }

    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<AlbumsModel>() {
            override fun areItemsTheSame(oldItem: AlbumsModel, newItem: AlbumsModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: AlbumsModel, newItem: AlbumsModel): Boolean =
                oldItem == newItem
        }
    }
}