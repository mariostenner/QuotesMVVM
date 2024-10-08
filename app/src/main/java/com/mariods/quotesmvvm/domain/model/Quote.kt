package com.mariods.quotesmvvm.domain.model

import com.mariods.quotesmvvm.data.database.entities.QuoteEntity
import com.mariods.quotesmvvm.data.network.model.QuoteModel

data class Quote(val quote: String, val author: String)


// Mapper para mapear la informacion de manera correcta entre un metodo y otro

fun QuoteModel.toDomain() = Quote(quote,author)

fun QuoteEntity.toDomain() = Quote(quote,author)
