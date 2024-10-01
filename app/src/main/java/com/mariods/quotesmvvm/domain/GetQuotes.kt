package com.mariods.quotesmvvm.domain

import com.mariods.quotesmvvm.data.QuoteRepository
import com.mariods.quotesmvvm.data.model.QuoteModel


// User case
class GetQuotes {
    private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel>? = repository.getAllQuotes()
}