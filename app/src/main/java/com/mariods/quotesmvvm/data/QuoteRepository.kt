package com.mariods.quotesmvvm.data

import com.mariods.quotesmvvm.data.database.dao.QuoteDao
import com.mariods.quotesmvvm.data.database.entities.QuoteEntity
import com.mariods.quotesmvvm.data.network.QuoteService
import com.mariods.quotesmvvm.domain.model.Quote
import com.mariods.quotesmvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {
    //private val api = QuoteService()
    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response = api.getQuotes()
        return response.map {
            it.toDomain()
        }
    }

    suspend fun getAllQuotesFromDataBase(): List<Quote> {
        val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }
}