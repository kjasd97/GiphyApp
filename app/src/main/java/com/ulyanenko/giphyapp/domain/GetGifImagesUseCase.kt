package com.ulyanenko.giphyapp.domain

import javax.inject.Inject

class GetGifImagesUseCase @Inject constructor (private val gifImageRepository: GifImageRepository) {
    suspend fun getGifImages():List<GifImage>{
        return gifImageRepository.loadImages()
    }

}