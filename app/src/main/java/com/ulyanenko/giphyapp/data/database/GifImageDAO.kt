package com.ulyanenko.giphyapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ulyanenko.giphyapp.domain.GifImage

@Dao
interface GifImageDAO {

    @Query("SELECT * FROM favourite_gifs")
   suspend fun getAllFavouriteGifs(): List<GifImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGifImage(gifs: GifImageEntity)

    @Query("Delete FROM favourite_gifs where url = :url")
    suspend fun removeMovie(url: String)

    @Query("SELECT * FROM favourite_gifs where url = :url")
    suspend fun getFavouriteGif(url: String): GifImage?
}