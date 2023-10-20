package com.ulyanenko.giphyapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_gifs")
data class GifImageEntity (

    @PrimaryKey()
    val url:String

)