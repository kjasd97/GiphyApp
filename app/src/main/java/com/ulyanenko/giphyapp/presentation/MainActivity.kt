package com.ulyanenko.giphyapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ulyanenko.giphyapp.databinding.ActivityMainBinding
import com.ulyanenko.giphyapp.domain.GifImage
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var gifsAdapter: GifImageAdapter


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        gifsAdapter = GifImageAdapter()


        binding.recycleView.adapter = gifsAdapter
        binding.recycleView.layoutManager = GridLayoutManager(this, 2)



        lifecycleScope.launch {
            launch {
                mainViewModel.gifs.collect {
                    gifsAdapter.submitList(it)
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

        lifecycleScope.launch {
            gifsAdapter.setOnReachEndListener(object : GifImageAdapter.OnReachEndListener {
                override fun onReachEnd() {
                    mainViewModel.loadGifs()
                }
            })
        }

        gifsAdapter.setOnGifImageClickListener(object : GifImageAdapter.OnGifImageClickListener{
            override fun onGigImageClick(gifImage: GifImage) {
                val intent = DetailGifImageActivity.newIntent(this@MainActivity, gifImage)
                startActivity(intent)
            }

        })

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val search = p0.toString()
                if (search.isNotBlank()) {
                    mainViewModel.loadGifsBySearch(search)
                }
            }

        })


    }


}