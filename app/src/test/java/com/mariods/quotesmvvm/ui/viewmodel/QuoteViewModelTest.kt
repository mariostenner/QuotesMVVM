package com.mariods.quotesmvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mariods.quotesmvvm.domain.GetQuotes
import com.mariods.quotesmvvm.domain.GetRandomQuote
import com.mariods.quotesmvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest{

    @RelaxedMockK
    lateinit var getQuotesUseCase: GetQuotes

    @RelaxedMockK
    lateinit var getRandomQuote: GetRandomQuote

    lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onCreate(){
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase,getRandomQuote)

        Dispatchers.setMain(Dispatchers.Unconfined) // Se cambia el dispatcher para correr las corrutinas

    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }


    @Test
    fun whenViewModelIsCreatedAtTheFirstTimeGetAllQuotesAndSetTheFirstValue()= runTest{

        //given
        val quote = listOf(Quote("hola", "Mario"),Quote("hola2", "Mario"))
        coEvery { getQuotesUseCase() } returns quote
        //when

        quoteViewModel.onCreate()

        //then
        assert(quoteViewModel.quoteModel.value == quote.first())

    }

    @Test
    fun whenRandomQuoteReturnAQuoteSetOnTheLiveData() = runTest {

        //given
        val quote = Quote("hola", "Mario")
        coEvery { getRandomQuote() } returns quote
        //when

        quoteViewModel.randomQuote()

        //then

        assert(quoteViewModel.quoteModel.value == quote)

    }

    @Test
    fun whenRandomQuoteReturnANullKeepTheLastValue() = runTest {

        //given
        val quote = Quote("hola", "Mario")
        quoteViewModel.quoteModel.value = quote
        coEvery { getRandomQuote() } returns null
        //when

        quoteViewModel.randomQuote()

        //then

        assert(quoteViewModel.quoteModel.value == quote)

    }


}