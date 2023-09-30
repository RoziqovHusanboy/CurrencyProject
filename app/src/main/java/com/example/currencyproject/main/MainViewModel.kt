package com.example.currencyproject.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyproject.utils.ConvertEvent
import com.example.currencyproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val mainRepository: MainRepository):ViewModel() {

    private val _conversion = MutableStateFlow<ConvertEvent>(ConvertEvent.Empty)
    val conversion:StateFlow<ConvertEvent> get() = _conversion


    fun getConvertRate(
        from:String,
        to:String,
        amount:String
    ){
        if (amount.isBlank()){
            return
        }
        viewModelScope.launch {

            _conversion.value = ConvertEvent.Loading

            val result = mainRepository.convertRate(from, to, amount)
            when(result){
                is Resource.Error -> {
                    _conversion.value = ConvertEvent.Error(result.massage)
                }
                is Resource.Success -> {
                    if (result.data!=null){
                        _conversion.value = ConvertEvent.Succes(result.data)
                    }else{
                        _conversion.value = ConvertEvent.Error(null)
                    }
                }

            }
        }
    }

}