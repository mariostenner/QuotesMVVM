package com.mariods.quotesmvvm.data.network

import com.mariods.quotesmvvm.core.RetrofitHelper
import com.mariods.quotesmvvm.data.network.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class QuoteService @Inject constructor(private val api: QuoteApiClient) {
    //private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuotes(): List<QuoteModel>{
        return withContext(Dispatchers.IO){
            val response = api.getAllQuotes()
            response.body()?: emptyList()
        }

    }
}