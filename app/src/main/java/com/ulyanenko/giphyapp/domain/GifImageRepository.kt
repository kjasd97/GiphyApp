package com.ulyanenko.giphyapp.domain

import android.icu.text.StringSearch
import androidx.lifecycle.LiveData
import com.ulyanenko.giphyapp.data.database.GifImageEntity

interface GifImageRepository {

    suspend fun loadImages():List<GifImage>

    suspend fun loadImagesBySearch(search: String):List<GifImage>

    suspend fun addGifToDb(gifImageEntity: GifImageEntity)

    suspend fun getGifsFromDb():List<GifImage>

    suspend fun getGifFromDb(url: String):GifImage

    suspend fun deleteGifFromDb(url:String)

}