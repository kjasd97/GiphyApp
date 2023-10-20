package com.ulyanenko.giphyapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ulyanenko.giphyapp.R
import com.ulyanenko.giphyapp.domain.GifImage
import com.ulyanenko.giphyapp.presentation.util.GifDiffUtilCallback

class DetailGifImageAdapter : ListAdapter<GifImage, DetailGifImageAdapter.GifViewHolder>(
    GifDiffUtilCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_detail_gif_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifImage = getItem(position)
        Glide.with(holder.imageView)
            .load(gifImage.url)
            .into(holder.imageView)
    }


    class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById<ImageView>(R.id.imageViewGif)
    }

}