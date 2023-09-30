package com.example.currencyproject.utils

sealed class Resource<T>(data:T?,massage:String?=null){

    data class Success<T>(val data: T?):Resource<T>(data,null)
    data class Error<T>(val massage: String?):Resource<T>(null,massage)

}
