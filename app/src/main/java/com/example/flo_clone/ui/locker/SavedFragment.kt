package com.example.flo_clone.ui.locker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo_clone.R
import com.example.flo_clone.databinding.FragmentSavedBinding
import com.example.flo_clone.model.Album

class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding
    private lateinit var savedAdapter: SavedRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        val items = ArrayList<Album>()
        for (i in 0..10) {
            items.add(Album("Butter", "BTS", R.drawable.img_album_exp))
            items.add(Album("LILAC", "IU (아이유)", R.drawable.img_album_exp2))
        }
        setupRecyclerView(items)

        return binding.root
    }

    private fun setupRecyclerView(songs: ArrayList<Album>) {
        savedAdapter = SavedRecyclerAdapter(songs)
        binding.savedRv.layoutManager = LinearLayoutManager(context)
        binding.savedRv.adapter = savedAdapter
    }
}