package com.mariods.quotesmvvm.domain

import com.mariods.quotesmvvm.data.QuoteRepository
import com.mariods.quotesmvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetQuotesTest{

    @RelaxedMockK
    private lateinit var repository: QuoteRepository

    lateinit var getQuotes: GetQuotes

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotes = GetQuotes(repository)
    }

    @Test
    fun whenTheApiDoesntReturnAnythingThenGetValuesFromDatabase() = runBlocking{

        //Given

        coEvery { repository.getAllQuotesFromApi() } returns emptyList()

        //When

        getQuotes()

        //Then

        coVerify (exactly = 1){ repository.getAllQuotesFromDataBase() }

    }


    @Test
    fun whenTheApiReturnSomethingThenGetValuesFromApi()= runBlocking{

        //Given
        val listDummy: List<Quote> = listOf(Quote("Never Give up", "Mario"))
        coEvery {repository.getAllQuotesFromApi()} returns listDummy

        //When
        val response = getQuotes()

        //Then
        coVerify (exactly = 1){
            repository.clearQuotes()
            repository.insertQuotes(any())
        }

        coVerify(exactly = 0) { repository.getAllQuotesFromDataBase() }
        assert(listDummy == response)

    }




}