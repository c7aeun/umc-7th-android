package com.example.flo_clone.ui.locker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo_clone.databinding.ItemSongBinding
import com.example.flo_clone.model.Album

class SavedRecyclerAdapter(
    private val items: ArrayList<Album>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val switchStates = MutableList(items.size) { false }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SongViewHolder).bind(items[position])
    }

    private fun deleteItem(position: Int) {
        items.removeAt(position)
        switchStates.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class SongViewHolder(
        private val binding: ItemSongBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Album) {
            binding.songSingerTv.text = song.singer
            binding.songTitleTv.text = song.title
            binding.songAlbumIv.setImageResource(song.img)

            // 기존 리스너 제거 (뷰 재활용 시 리스너 중복 등록 방지)
            binding.switch1.setOnCheckedChangeListener(null)
            binding.switch1.isChecked = switchStates[adapterPosition]

            binding.switch1.setOnCheckedChangeListener { _, isChecked ->
                switchStates[adapterPosition] = isChecked
            }

            binding.songMoreIv.setOnClickListener {
                deleteItem(adapterPosition)
            }
        }
    }
}