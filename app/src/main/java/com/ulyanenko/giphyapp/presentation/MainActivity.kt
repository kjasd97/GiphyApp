package com.ulyanenko.giphyapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ulyanenko.giphyapp.R
import com.ulyanenko.giphyapp.databinding.ActivityMainBinding
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
        gifsAdapter = GifImageAdapter(this)


        binding.recycleView.adapter = gifsAdapter
        binding.recycleView.layoutManager = GridLayoutManager(this, 2)

        lifecycleScope.launch {
            mainViewModel.gifs.collect {
                gifsAdapter.gifs = it
            }
        }




    }


}