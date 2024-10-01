package com.mariods.quotesmvvm.data.network

import com.mariods.quotesmvvm.data.model.QuoteModel
import retrofit2.Response

import retrofit2.http.GET

interface QuoteApiClient {
    @GET("/.json")
    suspend fun getAllQuotes(): Response<List<QuoteModel>>

}