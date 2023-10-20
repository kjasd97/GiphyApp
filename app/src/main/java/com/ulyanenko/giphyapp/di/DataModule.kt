package com.ulyanenko.giphyapp.di

import android.app.Application
import com.ulyanenko.giphyapp.data.database.AppDataBase
import com.ulyanenko.giphyapp.data.network.ApiFactory
import com.ulyanenko.giphyapp.data.network.ApiService
import com.ulyanenko.giphyapp.data.repositoryImpl.GifImageRepositoryImpl
import com.ulyanenko.giphyapp.domain.GifImageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindRepository(impl: GifImageRepositoryImpl): GifImageRepository

    companion object{

        @Provides
        @Singleton
        fun provideDataBase(application: Application): AppDataBase {
            return AppDataBase.getInstance(application)
        }

        @Provides
        @Singleton
        fun provideApiService():ApiService{
            return ApiFactory.apiService
        }

    }

}