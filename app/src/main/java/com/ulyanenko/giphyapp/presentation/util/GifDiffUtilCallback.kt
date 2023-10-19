package com.ulyanenko.giphyapp.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.ulyanenko.giphyapp.domain.GifImage

class GifDiffUtilCallback() : DiffUtil.ItemCallback<GifImage>() {
    override fun areItemsTheSame(oldItem: GifImage, newItem: GifImage): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: GifImage, newItem: GifImage): Boolean {
        return oldItem == newItem
    }
}