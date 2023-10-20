package com.ulyanenko.giphyapp.domain

data class GifImage(
    val url:String,
    val deleted:Boolean =false
): java.io.Serializable