package com.example.flo_clone.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo_clone.databinding.ItemAlbumBinding
import com.example.flo_clone.model.Album

class AlbumRecyclerAdapter(
    private val albumList: ArrayList<Album>,
    private val listener: AlbumAdapterListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AlbumViewHolder).bind(albumList[position])
    }

    inner class AlbumViewHolder(
        private val binding: ItemAlbumBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.albumTitleTv.text = album.title
            binding.albumSingerTv.text = album.singer
            binding.albumImgIv.setImageResource(album.img)
            binding.albumPlayIv.setOnClickListener {
                listener.albumPlayClickListener(album)
            }
            binding.albumImgIv.setOnClickListener {
                listener.albumClickListener(album)
            }
        }
    }
}