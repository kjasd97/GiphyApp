package com.ulyanenko.giphyapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ulyanenko.giphyapp.databinding.ActivityMainBinding
import com.ulyanenko.giphyapp.domain.GifImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var gifsAdapter: GifImageAdapter
    private var searchRequest: String? = null

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        gifsAdapter = GifImageAdapter()

        binding.recycleView.itemAnimator = null
        binding.recycleView.adapter = gifsAdapter
        binding.recycleView.layoutManager = GridLayoutManager(this, 2)

        gifsAdapter.deleteButtonClickListener = {
            mainViewModel.deleteGifImage(it)
        }


        lifecycleScope.launch {
            launch {
                mainViewModel.gifs.collect {
                    gifsAdapter.submitList(it.filterNot { it.deleted })
                }
            }

            launch {
                mainViewModel.loading.collect {
                    if (it) {
                        binding.progressBarLoading.visibility = View.VISIBLE
                    } else {
                        binding.progressBarLoading.visibility = View.GONE
                    }
                }

            }
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                searchRequest = p0.toString()
                if (!searchRequest.isNullOrBlank()) {
                    mainViewModel.loadGifsBySearch(searchRequest)
                } else {
                    mainViewModel.loadGifs()
                }
            }

        })

        gifsAdapter.setOnGifImageClickListener(object : GifImageAdapter.OnGifImageClickListener {
            override fun onGifImageClick(gifImage: GifImage) {
                val search = searchRequest ?: ""

                val intent = if (search.isNotBlank()) {
                    DetailGifImageActivity.newIntent(this@MainActivity, gifImage, search)
                } else {
                    DetailGifImageActivity.newIntent(this@MainActivity, gifImage)
                }
                startActivity(intent)
            }
        })


    }


}