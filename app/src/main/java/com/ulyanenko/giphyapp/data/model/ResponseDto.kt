package com.ulyanenko.giphyapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("data") val res: List<DataObjectDto>
)

data class DataObjectDto(
    @SerializedName("images") val images: DataImageDto
)

data class DataImageDto(
    @SerializedName("original") val gifImage: GifImageDto
)

data class GifImageDto(
    val url:String
)