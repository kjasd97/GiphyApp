package com.ulyanenko.giphyapp.data.network


import com.ulyanenko.giphyapp.data.model.ResponseDto
import retrofit2.http.GET


interface ApiService {

    @GET("gifs/trending?api_key=EyWsz2xYw7sWtHMkbpb92oVQb6RvaaUE&limit=50&offset=0&rating=g&bundle=messaging_non_clips")
    suspend fun loadImages(): ResponseDto




}