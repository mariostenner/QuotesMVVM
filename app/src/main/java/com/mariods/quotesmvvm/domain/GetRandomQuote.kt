package com.mariods.quotesmvvm.domain

import com.mariods.quotesmvvm.data.QuoteRepository
import com.mariods.quotesmvvm.data.model.QuoteModel
import com.mariods.quotesmvvm.data.model.QuoteProvider

class GetRandomQuote {

    operator fun invoke(): QuoteModel?{
        val quotes = QuoteProvider.quotes
        if(quotes.isNotEmpty()){
            val randomNumber = (0..quotes.size-1).random()
            return quotes[randomNumber]
        }
        return null
    }
}