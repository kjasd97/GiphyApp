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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailGifImageActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailGifImageBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: DetailGifImageViewModel
    private lateinit var gifAdapter: DetailGifImageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DetailGifImageViewModel::class.java]

        val gifImage = intent.getSerializableExtra(EXTRA_GIF_IMAGE) as GifImage
        val searchRequest = intent.getStringExtra(EXTRA_SEARCH_REQUEST)


        lifecycleScope.launch {

            viewModel.gif.collect {
                gifAdapter = DetailGifImageAdapter()
                binding.viewPager.adapter = gifAdapter
                val filteredList = it.filterNot { it.deleted }
                gifAdapter.submitList(filteredList)

                val itemToSet = filteredList.indexOf(gifImage)

                binding.viewPager.setCurrentItem(itemToSet, false)
            }
        }

        if (searchRequest != null) {
            viewModel.loadGifsBySearch(searchRequest)
        } else {
            viewModel.loadGifs()
        }

    }

    companion object {
        fun newIntent(context: Context, gifImage: GifImage): Intent {
            val intent = Intent(context, DetailGifImageActivity::class.java)
            intent.putExtra(EXTRA_GIF_IMAGE, gifImage)
            return intent
        }

        fun newIntent(context: Context, gifImage: GifImage, searchRequest: String): Intent {
            val intent = newIntent(context, gifImage)
            intent.putExtra(EXTRA_SEARCH_REQUEST, searchRequest)
            return intent
        }

        private const val EXTRA_GIF_IMAGE = "gif"
        private const val EXTRA_SEARCH_REQUEST = "searchRequest"


    }
}