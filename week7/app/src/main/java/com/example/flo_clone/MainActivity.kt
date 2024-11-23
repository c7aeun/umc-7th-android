package com.example.flo_clone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.flo_clone.databinding.ActivityMainBinding
import com.example.flo_clone.model.Song
import com.example.flo_clone.model.SongDatabase
import com.example.flo_clone.ui.home.HomeFragment
import com.example.flo_clone.ui.locker.LockerFragment
import com.example.flo_clone.ui.look.LookFragment
import com.example.flo_clone.ui.search.SearchFragment
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var song: Song = Song()
    private val gson: Gson = Gson()

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                Toast.makeText(this, data?.getStringExtra("title"), Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_FLOclone)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputDummySongs()

        initBottomNavigation()

        // main_player_cl 눌렀을 때 SongActivity로 전환 리스너 설정
        binding.mainPlayerCl.setOnClickListener {
            val editor = getSharedPreferences("song", MODE_PRIVATE).edit()
            editor.putInt("songId", song.id)
            editor.apply()

            val intent = Intent(this, SongActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val spf = getSharedPreferences("song", MODE_PRIVATE)
        val songId = spf.getInt("sondId", 0)

        val songDB = SongDatabase.getInstance(this)!!

        song = if (songId == 0){
            songDB.songDao().getSongs(1)
        } else{
            songDB.songDao().getSongs(songId)
        }

        Log.d("song ID", song.id.toString())

        setMiniPlayer(song)
    }

    private fun initBottomNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, HomeFragment())
            .commitAllowingStateLoss()

        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, HomeFragment())
                        .commitAllowingStateLoss()
                    true
                }

                R.id.navigation_look -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, LookFragment())
                        .commitAllowingStateLoss()
                    true
                }

                R.id.navigation_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, SearchFragment())
                        .commitAllowingStateLoss()
                    true
                }

                R.id.navigation_locker -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, LockerFragment())
                        .commitAllowingStateLoss()
                    true
                }

                else -> false
            }
        }
    }

    fun setMiniPlayer(song: Song) {
        binding.mainMiniplayerTitleTv.text = song.title
        binding.mainMiniplayerSingerTv.text = song.singer
        binding.mainPlayerSeekbar.progress = song.second * 100000 / song.playTime
    }

    private fun inputDummySongs() {
        val songDB = SongDatabase.getInstance(this)
        val songs = songDB?.songDao()?.getSongs()

        if (songs != null) {
            if (songs.isNotEmpty()) return
        }

        songDB?.songDao()?.insert(
            Song(
                "Butter",
                "BTS",
                0,
                230,
                false,
                "music_butter",
                R.drawable.img_album_exp,
                false,
            )
        )
        songDB?.songDao()?.insert(
            Song(
                "Lilac",
                "아이유",
                0,
                240,
                false,
                "music_lilac",
                R.drawable.img_album_exp2,
                false
            )
        )

        val _songs = songDB?.songDao()?.getSongs()
        Log.d("DB data", _songs.toString())
    }
}