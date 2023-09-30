package com.example.currencyproject.data

import com.example.currencyproject.data.models.ExchangeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ConverterApi {
    @GET("convert")
    @Headers("apikey:LKyTxjFxhZvazCbkmfSCkqoQ9LZJsokW")
    suspend fun convertRate(
        @Query("from") from:String,
        @Query("to") to:String,
        @Query("amount") amount:String
    ):Response<ExchangeResponse>

}