package com.leboncoin.test.wallyd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.test.wallyd.R
import com.leboncoin.test.wallyd.databinding.AlbumItemBinding
import com.leboncoin.test.wallyd.databinding.SeparatorItemBinding
import com.leboncoin.test.wallyd.model.AlbumSeparatorModel
import com.squareup.picasso.Picasso

class AlbumsAdapter :
    PagingDataAdapter<AlbumSeparatorModel, RecyclerView.ViewHolder>(diffCallback) {

    private lateinit var binding: AlbumItemBinding
    private lateinit var separatorItemBinding: SeparatorItemBinding

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class AlbumSeparatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.album_item -> {
                binding = DataBindingUtil.inflate(inflater, R.layout.album_item, parent, false)
                AlbumsViewHolder(
                    binding.root
                )
            }
            else -> {
                separatorItemBinding =
                    DataBindingUtil.inflate(inflater, R.layout.separator_item, parent, false)

                AlbumSeparatorViewHolder(
                    separatorItemBinding.root
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val albumSeparatorModel: AlbumSeparatorModel = getItem(position)!!) {
            is AlbumSeparatorModel.AlbumsItem -> {
                binding.textViewAlbumTitle.text = albumSeparatorModel.albumsModel.title
                binding.textViewId.text = albumSeparatorModel.albumsModel.id.toString()
                Picasso.get().load(albumSeparatorModel.albumsModel.url)
                    .into(binding.imageViewAlbumImage)
                holder.itemView.setOnClickListener(View.OnClickListener {
                    Toast.makeText(
                        holder.itemView.context,
                        " item ${albumSeparatorModel.albumsModel.id}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                })
            }
            is AlbumSeparatorModel.SeparatorModelItem -> {
                separatorItemBinding.separatorDescription.text = albumSeparatorModel.description
            }
        }

    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AlbumSeparatorModel.AlbumsItem -> R.layout.album_item
            is AlbumSeparatorModel.SeparatorModelItem -> R.layout.separator_item
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<AlbumSeparatorModel>() {
            override fun areItemsTheSame(
                oldItem: AlbumSeparatorModel,
                newItem: AlbumSeparatorModel
            ): Boolean {
                return (oldItem is AlbumSeparatorModel.AlbumsItem && newItem is AlbumSeparatorModel.AlbumsItem &&
                        oldItem.albumsModel.id == newItem.albumsModel.id) ||
                        (oldItem is AlbumSeparatorModel.SeparatorModelItem && newItem is AlbumSeparatorModel.SeparatorModelItem &&
                                oldItem.description == newItem.description)
            }

            override fun areContentsTheSame(
                oldItem: AlbumSeparatorModel,
                newItem: AlbumSeparatorModel
            ): Boolean =
                oldItem == newItem
        }
    }
}