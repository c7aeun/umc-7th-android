package com.example.flo_clone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo_clone.MainActivity
import com.example.flo_clone.R
import com.example.flo_clone.databinding.FragmentHomeBinding
import com.example.flo_clone.model.Album
import com.example.flo_clone.model.Song
import com.example.flo_clone.ui.album.AlbumFragment

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var listener: AlbumAdapterListener
    private lateinit var albumRvAdapter: AlbumRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        listener = object : AlbumAdapterListener {
            override fun albumPlayClickListener(album: Album) {
                val song = Song(album.title, album.singer, 0, 60, false, "music_lilac")
                (context as MainActivity).setMiniPlayer(song)
            }
            override fun albumClickListener(album: Album) {
                val albumFragment = AlbumFragment()
                albumFragment.arguments = Bundle().apply {
                    putString("title", album.title)
                    putString("singer", album.singer)
                    putInt("img", album.img)
                }
                (context as MainActivity)
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, albumFragment)
                    .commitAllowingStateLoss()
            }
        }

        // 오늘의 노래 RecyclerView 어댑터 적용
        val songList = ArrayList<Album>()
        for(i in 1..10) {
            songList.add(Album("BTS", "Butter", R.drawable.img_album_exp))
            songList.add(Album("아이유 (IU)", "LILAC", R.drawable.img_album_exp2))
        }
        setupRecyclerView(songList)

        // 배너 ViewPager 어댑터 적용
        val bannerAdapter = BannerViewPagerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.homeBannerVp.adapter = bannerAdapter

        return binding.root
    }

    private fun setupRecyclerView(albums: ArrayList<Album>) {
        albumRvAdapter = AlbumRecyclerAdapter(albums, listener)
        binding.homeTodayMusicTotalRv.adapter = albumRvAdapter
    }
}