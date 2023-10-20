package com.ulyanenko.giphyapp.data.repositoryImpl

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.ulyanenko.giphyapp.data.database.GifImageEntity
import com.ulyanenko.giphyapp.data.mapper.GifImageMapper
import com.ulyanenko.giphyapp.data.network.ApiFactory
import com.ulyanenko.giphyapp.domain.GifImage
import com.ulyanenko.giphyapp.domain.GifImageRepository
import com.ulyanenko.giphyapp.data.database.AppDataBase

class GifImageRepositoryImpl(application: Application) : GifImageRepository {

    private val apiService = ApiFactory.apiService
    private val db = AppDataBase.getInstance(application)
    private val gifDAO = db.methodsGifImageDao()
    private val deletedGifDAO = db.deletedMethodsGifImageDao()



    private val mapper = GifImageMapper()

    override suspend fun loadImages(): List<GifImage> {
        val listDto = try {
            apiService.loadImages().res.map {
                it.images.gifImage
            }
        }catch (e: Exception){
            Log.d("appError", e.message.toString())
            listOf()
        }
        if (listDto.isNotEmpty()){
            Log.d("appError", "isNotEmpty")
            gifDAO.insertListGifImage(mapper.mapFromListGifImageToEntity(listDto))
        }

        val allGifs = gifDAO.getAllFavouriteGifs().toMutableList()
        val deletedGifs = deletedGifDAO.getAllDeletedGifs()

        allGifs.forEachIndexed { index, gifImage ->
            deletedGifs.forEach {
             if (it.url==gifImage.url){
                 allGifs[index] = it
             }
            }
        }
        return allGifs.toList()
    }

    override suspend fun loadImagesBySearch(search: String): List<GifImage> {
        val listDto = apiService.searchImages(search).res.map {
            it.images.gifImage
        }
        return mapper.mapResponseToGifImage(listDto)
    }

    override suspend fun addGifToDb(gifImage: GifImage) {
        gifDAO.insertGifImage(mapper.mapFromGifImageToEntity(gifImage))
    }

    override suspend fun getGifsFromDb(): List<GifImage> {
        return gifDAO.getAllFavouriteGifs()
    }

    override suspend fun getGifFromDb(url: String): GifImage? {
        return gifDAO.getFavouriteGif(url)
    }

    override suspend fun deleteGifFromDb(url: String) {
        gifDAO.removeMovie(url)
    }

    override suspend fun deleteGif(gifImage: GifImage) {
        deletedGifDAO.deleteGifImage(mapper.mapFromGifImageToDeletedEntity(gifImage))
    }

}