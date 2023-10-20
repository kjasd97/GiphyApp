package com.ulyanenko.giphyapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GifImageEntity::class, DeletedGifImageEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {


    abstract fun methodsGifImageDao(): GifImageDAO
    abstract fun deletedMethodsGifImageDao(): DeletedImagesDao


    companion object {

        private var gifsDataBase: AppDataBase? = null

        fun getInstance(application: Application): AppDataBase {
            gifsDataBase?.let {
                return it
            }

            val db = Room.databaseBuilder(
                application,
                AppDataBase::class.java,
                "gifs_db"
            ).build()
            gifsDataBase = db
            return db
        }

    }

}