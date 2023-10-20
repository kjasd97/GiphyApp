package com.ulyanenko.giphyapp.domain

class DeleteGifUseCase(private val gifImageRepository: GifImageRepository) {
    suspend fun deleteGif(gifImage: GifImage){
        gifImageRepository.deleteGif(gifImage)
    }
}