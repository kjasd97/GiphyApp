package com.ulyanenko.giphyapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ulyanenko.giphyapp.R
import com.ulyanenko.giphyapp.data.model.DataObjectDto
import com.ulyanenko.giphyapp.data.model.ResponseDto
import com.ulyanenko.giphyapp.domain.GifImage

class GifImageAdapter(val context: Context) :
    RecyclerView.Adapter<GifImageAdapter.GifImageViewHolder>() {

    var gifs = listOf<GifImage>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GifImageAdapter.GifImageViewHolder {
        return GifImageViewHolder(
            LayoutInflater.from(context).inflate(R.layout.gif_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GifImageAdapter.GifImageViewHolder, position: Int) {
        val data = gifs[position]

        Glide.with(context).load(data.url).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    class GifImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.image)
    }

}