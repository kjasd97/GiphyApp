package com.ulyanenko.giphyapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ulyanenko.giphyapp.data.mapper.GifImageMapper
import com.ulyanenko.giphyapp.databinding.ActivityDetailGifImageBinding
import com.ulyanenko.giphyapp.domain.GifImage
import kotlinx.coroutines.launch

class DetailGifImageActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailGifImageBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: DetailGifImageViewModel

    private val mapper = GifImageMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DetailGifImageViewModel::class.java]

        val gifImage = intent.getSerializableExtra("gif") as GifImage

        Glide.with(this).load(gifImage.url).into(binding.imageViewGif)

        val starOff = ContextCompat.getDrawable(
            this@DetailGifImageActivity,
            android.R.drawable.star_big_off
        )
        val starOn = ContextCompat.getDrawable(
            this@DetailGifImageActivity,
            android.R.drawable.star_big_on
        )
        lifecycleScope.launch {
            viewModel.gif.collect{
                if (it == null) {
                    binding.imageViewStar.setImageDrawable(starOff)
                    binding.imageViewStar.setOnClickListener {
                        viewModel.insertGif(mapper.mapFromGifImageToEntity(gifImage))
                    }
                } else {
                    binding.imageViewStar.setImageDrawable(starOn)
                    binding.imageViewStar.setOnClickListener {
                        viewModel.removeGif(gifImage.url)
                    }
                }
            }
        }

    }

    companion object {
        fun newIntent(context: Context, gifImage: GifImage): Intent {
            val intent = Intent(context, DetailGifImageActivity::class.java)
            intent.putExtra("gif", gifImage)
            return intent
        }
    }
}