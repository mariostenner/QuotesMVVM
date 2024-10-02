package com.mariods.quotesmvvm.ui.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.mariods.quotesmvvm.databinding.ActivityMainBinding
import com.mariods.quotesmvvm.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        quoteViewModel.quoteModel.observe(this, Observer { quote->
            binding.tvQuote.text = quote.quote
            binding.tvAuthor.text = quote.author
        })

        binding.vContainer.setOnClickListener {
            quoteViewModel.randomQuote()
        }

        quoteViewModel.isLoading.observe(this, Observer { loader ->
            binding.pbProgressBar.isVisible = loader
        })


    }

}