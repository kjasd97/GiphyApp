package com.ulyanenko.giphyapp.domain

import android.icu.text.StringSearch

interface GifImageRepository {

    suspend fun loadImages():List<GifImage>

    suspend fun loadImagesBySearch(search: String):List<GifImage>

}