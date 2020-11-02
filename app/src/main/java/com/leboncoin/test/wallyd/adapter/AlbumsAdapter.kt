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
import com.leboncoin.test.wallyd.databinding.SeparatorItemBinding
import com.leboncoin.test.wallyd.model.AlbumSeparatorModel
import com.leboncoin.test.wallyd.model.AlbumsModel
import com.squareup.picasso.Picasso

class AlbumsAdapter :
    PagingDataAdapter<AlbumsModel, RecyclerView.ViewHolder>(diffCallback) {

    private lateinit var binding: AlbumItemBinding
    private lateinit var separatorItemBinding: SeparatorItemBinding

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class AlbumSeparatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.album_item, parent, false)

        return AlbumsViewHolder(binding.root)
/*
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

 */
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val albumSeparatorModel: AlbumsModel = getItem(position)!!
        albumSeparatorModel.let {
            binding.textViewAlbumTitle.text = it.title
            binding.textViewId.text = it.id.toString()
            Picasso.get().load(it.url)
                .into(binding.imageViewAlbumImage)
            /*
            when (albumSeparatorModel) {
                is AlbumSeparatorModel.AlbumsItem -> {
                    binding.textViewAlbumTitle.text = albumSeparatorModel.albumsModel?.title
                    binding.textViewId.text = albumSeparatorModel.albumsModel?.id.toString()
                    Picasso.get().load(albumSeparatorModel.albumsModel?.url)
                        .into(binding.imageViewAlbumImage)
                }
                is AlbumSeparatorModel.SeparatorModelItem -> {
                    separatorItemBinding.separatorDescription.text = albumSeparatorModel.description
                }
            }

             */


        }
        /*
        holder.itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                holder.itemView.context,
                " item ${getItem(position)?.id}",
                Toast.LENGTH_SHORT
            )
                .show()
        })

         */

    }
/*
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AlbumSeparatorModel.AlbumsItem -> R.layout.album_item
            is AlbumSeparatorModel.SeparatorModelItem -> R.layout.separator_item
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

 */

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<AlbumsModel>() {
            override fun areItemsTheSame(oldItem: AlbumsModel, newItem: AlbumsModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: AlbumsModel, newItem: AlbumsModel): Boolean =
                oldItem == newItem
        }
    }
/*
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<AlbumSeparatorModel>() {
            override fun areItemsTheSame(
                oldItem: AlbumSeparatorModel,
                newItem: AlbumSeparatorModel
            ): Boolean {
                return (oldItem is AlbumSeparatorModel.AlbumsItem && newItem is AlbumSeparatorModel.AlbumsItem &&
                        oldItem.albumsModel?.id == newItem.albumsModel?.id) ||
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

 */
}