package com.example.currencyproject.main

import com.example.currencyproject.data.ConverterApi
import com.example.currencyproject.data.models.ExchangeResponse
import com.example.currencyproject.utils.Resource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor (
    private val api:ConverterApi
):MainRepository {

    override suspend fun convertRate(
        from: String,
        to: String,
        amount: String
    ): Resource<ExchangeResponse> {
       return try {
            val responce = api.convertRate(from,to,amount)
            if (responce.isSuccessful && responce.body()!=null){
               Resource.Success(responce.body())
            }else{
                Resource.Error(responce.message())
            }
        }catch (e:Exception){
            Resource.Error<ExchangeResponse>(e.message)
        }
    }
}