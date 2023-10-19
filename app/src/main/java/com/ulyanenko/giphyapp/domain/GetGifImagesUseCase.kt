package com.ulyanenko.giphyapp.domain

class GetGifImagesUseCase(private val gifImageRepository: GifImageRepository) {
    suspend fun getGifImages():List<GifImage>{
        return gifImageRepository.loadImages()
    }

}