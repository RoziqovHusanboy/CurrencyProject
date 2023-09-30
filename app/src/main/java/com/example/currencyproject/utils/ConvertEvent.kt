package com.example.currencyproject.utils

import com.example.currencyproject.data.models.ExchangeResponse

sealed class ConvertEvent{
    data class Succes(val result:ExchangeResponse):ConvertEvent()
    data class Error(val massage:String?):ConvertEvent()
    object Loading:ConvertEvent()
    object Empty:ConvertEvent()
}
