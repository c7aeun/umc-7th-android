package com.example.flo_clone.ui.locker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo_clone.R
import com.example.flo_clone.databinding.ItemSongBinding
import com.example.flo_clone.model.Song

class SavedRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val songs = ArrayList<Song>()

    override fun getItemCount(): Int = songs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SongViewHolder).bind(songs[position])
    }

    fun addItems(newItems: ArrayList<Song>) {
        songs.clear()
        songs.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class SongViewHolder(
        val binding: ItemSongBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.songSingerTv.text = song.singer
            binding.songTitleTv.text = song.title
            binding.songAlbumIv.setImageResource(song.coverImg ?: R.drawable.img_album_butter)
        }
    }
}