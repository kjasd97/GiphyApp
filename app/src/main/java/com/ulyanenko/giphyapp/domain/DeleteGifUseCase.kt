package com.ulyanenko.giphyapp.domain

import javax.inject.Inject

class DeleteGifUseCase @Inject constructor (private val gifImageRepository: GifImageRepository) {
    suspend fun deleteGif(gifImage: GifImage){
        gifImageRepository.deleteGif(gifImage)
    }
}