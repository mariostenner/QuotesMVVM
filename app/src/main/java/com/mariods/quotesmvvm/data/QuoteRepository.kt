package com.mariods.quotesmvvm.data

import com.mariods.quotesmvvm.data.model.QuoteModel
import com.mariods.quotesmvvm.data.model.QuoteProvider
import com.mariods.quotesmvvm.data.network.QuoteService

class QuoteRepository {
    private val api = QuoteService()
    suspend fun getAllQuotes(): List<QuoteModel>{
        val response = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}