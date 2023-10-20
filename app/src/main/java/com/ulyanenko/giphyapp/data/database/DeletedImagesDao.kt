package com.ulyanenko.giphyapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ulyanenko.giphyapp.domain.GifImage

@Dao
interface DeletedImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun deleteGifImage(gifs: DeletedGifImageEntity)
    @Query("SELECT * FROM deleted_gifs")
    suspend fun getAllDeletedGifs():List<GifImage>

}