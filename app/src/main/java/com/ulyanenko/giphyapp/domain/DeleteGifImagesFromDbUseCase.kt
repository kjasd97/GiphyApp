package com.ulyanenko.giphyapp.domain

class DeleteGifImagesFromDbUseCase (private val gifImageRepository: GifImageRepository) {

    suspend fun deleteGifFromDb(url:String){
        gifImageRepository.deleteGifFromDb(url)
    }

}