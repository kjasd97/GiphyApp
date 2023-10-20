package com.ulyanenko.giphyapp.domain

import androidx.lifecycle.LiveData
import com.ulyanenko.giphyapp.data.database.GifImageEntity

class GetGifFromDbUseCase(private val gifImageRepository: GifImageRepository) {

   suspend fun getGifFromDb (url: String): GifImage {
      return gifImageRepository.getGifFromDb(url)
   }

}