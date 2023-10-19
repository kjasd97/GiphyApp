package com.ulyanenko.giphyapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.ulyanenko.giphyapp.R
import com.ulyanenko.giphyapp.databinding.ActivityDetailGifImageBinding
import com.ulyanenko.giphyapp.databinding.ActivityMainBinding
import com.ulyanenko.giphyapp.domain.GifImage

class DetailGifImageActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailGifImageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val gifImage = intent.getSerializableExtra("gif") as GifImage

        Glide.with(this).load(gifImage.url).into( binding.imageViewGif)

        val starOff = ContextCompat.getDrawable(
            this@DetailGifImageActivity,
            android.R.drawable.star_big_off
        )
        val starOn = ContextCompat.getDrawable(
            this@DetailGifImageActivity,
            android.R.drawable.star_big_on
        )

        binding.imageViewStar.setImageDrawable(starOff)
    }

    companion object {
        fun newIntent(context: Context, gifImage: GifImage): Intent {
            val intent = Intent(context, DetailGifImageActivity::class.java)
            intent.putExtra("gif", gifImage)
            return intent
        }
    }
}