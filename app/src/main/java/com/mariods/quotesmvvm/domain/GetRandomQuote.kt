package com.mariods.quotesmvvm.domain

import com.mariods.quotesmvvm.data.QuoteRepository
import com.mariods.quotesmvvm.domain.model.Quote
import javax.inject.Inject


class GetRandomQuote @Inject constructor(private val repository: QuoteRepository){

    suspend operator fun invoke(): Quote? {

        val quotes = repository.getAllQuotesFromDataBase()
        if (quotes.isNotEmpty()) {
            val randomNumber = (0..quotes.size - 1).random()
            return quotes[randomNumber]
        }
        return null
    }
}