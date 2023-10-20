package com.ulyanenko.giphyapp.domain

import androidx.lifecycle.LiveData

class GetGifImagesFromDbUseCase(private val gifImageRepository: GifImageRepository) {

   suspend fun getGifFromDb (): List<GifImage> {
      return gifImageRepository.getGifsFromDb()
   }

}