package com.ulyanenko.giphyapp.data.mapper

import com.ulyanenko.giphyapp.data.database.DeletedGifImageEntity
import com.ulyanenko.giphyapp.data.database.GifImageEntity
import com.ulyanenko.giphyapp.data.model.GifImageDto
import com.ulyanenko.giphyapp.domain.GifImage
import javax.inject.Inject

class GifImageMapper @Inject constructor() {

    fun mapResponseToGifImage(gifImagesDto: List<GifImageDto>): List<GifImage> {

        val result = mutableListOf<GifImage>()

        for(gifImageDto in gifImagesDto){
            val gifImage = GifImage(
                url = gifImageDto.url
            )
            result.add(gifImage)
        }
        return result
    }

    fun mapFromGifImageToEntity(gifImage: GifImage):GifImageEntity{
        return GifImageEntity(url = gifImage.url, deleted = gifImage.deleted)
    }

    fun mapFromListGifImageToEntity(gifImages: List< GifImageDto>): List<GifImageEntity>{
        return gifImages.map {
            GifImageEntity(it.url, false)
        }
    }

    fun mapFromGifImageToDeletedEntity(gifImage: GifImage):DeletedGifImageEntity{
        return DeletedGifImageEntity(url = gifImage.url, true)
    }
}