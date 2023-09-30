package com.example.currencyproject.main

import com.example.currencyproject.data.models.ExchangeResponse
import com.example.currencyproject.utils.Resource

interface MainRepository {

    suspend fun convertRate(
        from:String,
        to:String,
        amount:String
    ):Resource<ExchangeResponse>

}