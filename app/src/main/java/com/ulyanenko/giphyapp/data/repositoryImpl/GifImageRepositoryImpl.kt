package com.ulyanenko.giphyapp.data.repositoryImpl

import android.app.Application
import androidx.lifecycle.LiveData
import com.ulyanenko.giphyapp.data.database.GifImageEntity
import com.ulyanenko.giphyapp.data.mapper.GifImageMapper
import com.ulyanenko.giphyapp.data.network.ApiFactory
import com.ulyanenko.giphyapp.domain.GifImage
import com.ulyanenko.giphyapp.domain.GifImageRepository
import com.ulyanenko.giphyapp.data.database.AppDataBase

class GifImageRepositoryImpl(application: Application) : GifImageRepository {

    private val apiService = ApiFactory.apiService
    private val gifDAO = AppDataBase.getInstance(application).methodsGifImageDao()

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

    override suspend fun addGifToDb(gifImageEntity: GifImageEntity) {
        gifDAO.insertGifImage(gifImageEntity)
    }

    override suspend fun getGifsFromDb(): List<GifImage> {
        return gifDAO.getAllFavouriteGifs()
    }

    override suspend fun getGifFromDb(url: String): GifImage {
        return gifDAO.getFavouriteGif(url)
    }

    override suspend fun deleteGifFromDb(url: String) {
        gifDAO.removeMovie(url)
    }

}