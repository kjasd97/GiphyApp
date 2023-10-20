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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DetailGifImageViewModel::class.java]

        val gifImage = intent.getSerializableExtra("gif") as GifImage

        Glide.with(this).load(gifImage.url).into(binding.imageViewGif)


    }

    companion object {
        fun newIntent(context: Context, gifImage: GifImage): Intent {
            val intent = Intent(context, DetailGifImageActivity::class.java)
            intent.putExtra("gif", gifImage)
            return intent
        }
    }
}