package com.mariods.quotesmvvm.domain

import com.mariods.quotesmvvm.data.QuoteRepository
import com.mariods.quotesmvvm.data.model.QuoteModel
import javax.inject.Inject


// User case
class GetQuotes @Inject constructor(private val repository: QuoteRepository) {
    //private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel>? = repository.getAllQuotes()
}