package com.example.flo_clone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo_clone.databinding.FragmentSavedBinding
import com.example.flo_clone.model.SongDatabase
import com.example.flo_clone.databinding.FragmentSavedSongBinding
import com.example.flo_clone.ui.locker.SavedRecyclerAdapter

class SavedSongFragment : Fragment() {
    lateinit var binding: FragmentSavedBinding
    lateinit var songDB: SongDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater, container, false)

        songDB = SongDatabase.getInstance(requireContext())!!

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        binding.savedRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val songRVAdapter = SavedRecyclerAdapter()

        binding.lockerSavedSongRecyclerView.adapter = songRVAdapter

        songRVAdapter.addSongs(songDB.songDao().getLikeSongs(true) as ArrayList<Song>)
    }
}