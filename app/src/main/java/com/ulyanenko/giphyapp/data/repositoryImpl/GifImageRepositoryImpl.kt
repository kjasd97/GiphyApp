package com.ulyanenko.giphyapp.data.repositoryImpl

import android.app.Application
import com.ulyanenko.giphyapp.data.mapper.GifImageMapper
import com.ulyanenko.giphyapp.data.network.ApiFactory
import com.ulyanenko.giphyapp.domain.GifImage
import com.ulyanenko.giphyapp.domain.GifImageRepository

class GifImageRepositoryImpl(application: Application) : GifImageRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = GifImageMapper()

    override suspend fun loadImages(): List<GifImage> {
        val listDto = apiService.loadImages().res.map {
            it.images.gifImage
        }
        return mapper.mapResponseToGifImage(listDto)
    }

    override suspend fun loadImagesBySearch(search: String): List<GifImage> {
        val listDto = apiService.searchImages(search).res.map {
            it.images.gifImage
        }
        return mapper.mapResponseToGifImage(listDto)
    }

}