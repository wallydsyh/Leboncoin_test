package com.leboncoin.test.wallyd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.test.wallyd.R
import com.leboncoin.test.wallyd.databinding.AlbumItemBinding
import com.leboncoin.test.wallyd.model.AlbumsModel
import com.squareup.picasso.Picasso

class AlbumsAdapter : PagingDataAdapter<AlbumsModel, AlbumsAdapter.AlbumsViewHolder>(diffCallback) {

    private lateinit var binding: AlbumItemBinding

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.album_item, parent, false)

        return AlbumsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val albumsModel : AlbumsModel? = getItem(position)
        binding.textViewAlbumTitle.text = albumsModel?.title
        binding.textViewAlbumId.text = albumsModel?.id.toString()
        Picasso.get().load(albumsModel?.url).into(binding.imageViewAlbumImage)
    }


    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<AlbumsModel>() {
            override fun areItemsTheSame(oldItem: AlbumsModel, newItem: AlbumsModel): Boolean =
                oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: AlbumsModel, newItem: AlbumsModel): Boolean =
                oldItem == newItem
        }
    }
}