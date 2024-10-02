package com.mariods.quotesmvvm.domain

import com.mariods.quotesmvvm.data.QuoteRepository
import com.mariods.quotesmvvm.data.model.QuoteModel
import com.mariods.quotesmvvm.data.model.QuoteProvider
import javax.inject.Inject


class GetRandomQuote @Inject constructor(private val quoteProvider: QuoteProvider){

    operator fun invoke(): QuoteModel?{
        val quotes = quoteProvider.quotes
        if(quotes.isNotEmpty()){
            val randomNumber = (0..quotes.size-1).random()
            return quotes[randomNumber]
        }
        return null
    }
}