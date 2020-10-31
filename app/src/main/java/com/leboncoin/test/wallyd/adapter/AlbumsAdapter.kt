package com.leboncoin.test.wallyd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.test.wallyd.R
import com.leboncoin.test.wallyd.databinding.AlbumItemBinding
import com.leboncoin.test.wallyd.model.AlbumsModel
import com.squareup.picasso.Picasso

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    private lateinit var binding: AlbumItemBinding
    private var albumsModel = emptyList<AlbumsModel>()

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.album_item, parent, false)

        return AlbumsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val albumsModel = albumsModel[position]
        binding.textViewAlbumTitle.text = albumsModel.title
        binding.textViewAlbumId.text = albumsModel.id.toString()
        Picasso.get().load(albumsModel.url).into(binding.imageViewAlbumImage)
    }


    override fun getItemCount(): Int {
        return albumsModel.size
    }

    fun setAlbumsData(albumsModel: List<AlbumsModel>) {
        this.albumsModel = albumsModel
        notifyDataSetChanged()
    }
}