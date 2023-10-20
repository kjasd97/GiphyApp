package com.ulyanenko.giphyapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deleted_gifs")
data class DeletedGifImageEntity(

    @PrimaryKey()
    val url: String,
    val deleted: Boolean

)