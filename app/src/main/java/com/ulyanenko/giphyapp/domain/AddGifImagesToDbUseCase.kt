package com.ulyanenko.giphyapp.domain

import com.ulyanenko.giphyapp.data.database.GifImageEntity

class AddGifImagesToDbUseCase(private val gifImageRepository: GifImageRepository) {

    suspend fun addGifToDb(gifImageEntity: GifImageEntity) {
        gifImageRepository.addGifToDb(gifImageEntity)
    }

}