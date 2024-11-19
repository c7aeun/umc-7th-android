package com.example.flo_clone.model

import androidx.room.*

@Dao
abstract class SongDao : Collection<Song> {
    @Insert
    abstract fun insert(song: Song)

    @Update
    abstract fun update(song: Song)

    @Delete
    abstract fun delete(song: Song)

    @Query(value = "SELECT * FROM SongTable")
    abstract fun getSongs(): List<Song>

    @Query(value = "SELECT * FROM SongTable WHERE id = :id")
    abstract fun getSongs(id: Int): Song

    @Query("UPDATE SongTable SET isLike= :isLike WHERE id = :id")
    abstract fun updateIsLikeById(isLike: Boolean, id: Int)

    @Query("SELECT * FROM SongTable WHERE isLike= :isLike")
    abstract fun getLikeSongs(isLike: Boolean): List<Song>
}