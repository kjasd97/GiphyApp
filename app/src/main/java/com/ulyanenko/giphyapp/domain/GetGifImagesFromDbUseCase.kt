package com.ulyanenko.giphyapp.domain

import javax.inject.Inject


class GetGifImagesFromDbUseCase @Inject constructor (private val gifImageRepository: GifImageRepository) {

   suspend fun getGifFromDb (): List<GifImage> {
      return gifImageRepository.getGifsFromDb()
   }

}