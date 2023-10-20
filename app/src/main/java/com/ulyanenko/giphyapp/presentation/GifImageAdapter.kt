package com.ulyanenko.giphyapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ulyanenko.giphyapp.R
import com.ulyanenko.giphyapp.domain.GifImage
import com.ulyanenko.giphyapp.presentation.util.GifDiffUtilCallback

class GifImageAdapter : ListAdapter<GifImage, GifImageAdapter.GifImageViewHolder>(GifDiffUtilCallback()) {

    private lateinit var onGifImageClickListener: OnGifImageClickListener
    var deleteButtonClickListener: ((GifImage) -> Unit)? = null


    fun setOnGifImageClickListener(onGifImageClickListener: OnGifImageClickListener) {
        this.onGifImageClickListener = onGifImageClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GifImageAdapter.GifImageViewHolder {
        return GifImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GifImageAdapter.GifImageViewHolder, position: Int) {
        val gifImage = getItem(position)

        Glide.with(holder.imageView.context).load(gifImage.url).into(holder.imageView)


        holder.itemView.setOnClickListener {
            if (onGifImageClickListener != null) {
                onGifImageClickListener.onGifImageClick(gifImage)
            }
        }

        val deleteButton = holder.itemView.findViewById<ImageButton>(R.id.deleteButton)

        deleteButton.setOnClickListener {
            deleteButtonClickListener?.invoke(gifImage)
        }


    }


    class GifImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.image)
    }


    interface OnGifImageClickListener {
        fun onGifImageClick(gifImage: GifImage)
    }


}