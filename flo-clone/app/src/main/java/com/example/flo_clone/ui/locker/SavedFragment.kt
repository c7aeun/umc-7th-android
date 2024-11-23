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
import com.example.flo_clone.model.Song
import com.example.flo_clone.model.SongDatabase

class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding
    lateinit var songDB: SongDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)

        songDB = SongDatabase.getInstance(requireContext())!!

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val savedAdapter = SavedRecyclerAdapter()
        binding.savedRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.savedRv.adapter = savedAdapter

        savedAdapter.addItems(songDB.songDao().getLikeSongs(true) as ArrayList<Song>)
    }
}