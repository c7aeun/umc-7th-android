package com.example.flo_clone.ui.home

import com.example.flo_clone.model.Album

interface AlbumAdapterListener {
    fun albumClickListener(album: Album)
    fun albumPlayClickListener(album: Album)
}