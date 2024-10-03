package com.mariods.quotesmvvm.domain

import com.mariods.quotesmvvm.data.QuoteRepository
import com.mariods.quotesmvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetRandomQuoteTest{

    @RelaxedMockK
    private lateinit var repository: QuoteRepository

    lateinit var getRandomQuote: GetRandomQuote

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuote = GetRandomQuote(repository)
    }

    @Test
    fun validateRepositoryIsEmpty() = runBlocking {

        //given

        coEvery { repository.getAllQuotesFromDataBase() } returns emptyList()

        //when

        val response = getRandomQuote()

        //then
        assert(response == null)
    }

    @Test
    fun validateRepositoryIsNotEmpty() = runBlocking {

        //given
        val listDummy = listOf(Quote("Never give up", " Mario"))
        coEvery { repository.getAllQuotesFromDataBase() } returns listDummy

        //when (cuando se llame al repositorio
        val response = getRandomQuote()

        //then
        assert(response == listDummy.first())

    }

}