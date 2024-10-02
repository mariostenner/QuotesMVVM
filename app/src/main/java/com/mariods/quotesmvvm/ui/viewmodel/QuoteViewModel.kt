package com.mariods.quotesmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariods.quotesmvvm.data.model.QuoteModel
import com.mariods.quotesmvvm.domain.GetQuotes
import com.mariods.quotesmvvm.domain.GetRandomQuote
import com.mariods.quotesmvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    var getQuotesUseCase: GetQuotes,
    var getRandomQuote: GetRandomQuote
) : ViewModel() {

    val quoteModel = MutableLiveData<Quote>()
    //var getQuotesUseCase = GetQuotes()
    //var getRandomQuote = GetRandomQuote()

    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.value = true
            val result = getQuotesUseCase()

            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.value = false
            }
        }
    }

    fun randomQuote() {


        viewModelScope.launch {
            isLoading.value = true
            val quote = getRandomQuote()
            if (quote != null) {
                quoteModel.value = quote
            }
            isLoading.value = false
        }




//        val currentQuote: QuoteModel = QuoteProvider.random()
//       quoteModel.value = currentQuote
    }


}