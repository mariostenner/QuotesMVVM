package com.mariods.quotesmvvm.domain

import com.mariods.quotesmvvm.data.QuoteRepository
import com.mariods.quotesmvvm.data.database.entities.toDataBase
import com.mariods.quotesmvvm.domain.model.Quote
import javax.inject.Inject


// User case
class GetQuotes @Inject constructor(private val repository: QuoteRepository) {
    //private val repository = QuoteRepository()

    suspend operator fun invoke(): List<Quote> {

        val quotes = repository.getAllQuotesFromApi()
        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.insertQuotes(quotes.map {
                it.toDataBase()
            })
            quotes
        } else {
            repository.getAllQuotesFromDataBase()
        }

    }

}