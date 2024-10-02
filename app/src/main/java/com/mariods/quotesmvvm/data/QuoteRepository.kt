package com.mariods.quotesmvvm.data

import com.mariods.quotesmvvm.data.model.QuoteModel
import com.mariods.quotesmvvm.data.model.QuoteProvider
import com.mariods.quotesmvvm.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val api : QuoteService, private val quoteProvider: QuoteProvider) {
    //private val api = QuoteService()
    suspend fun getAllQuotes(): List<QuoteModel>{
        val response = api.getQuotes()
        quoteProvider.quotes = response
        return response
    }
}