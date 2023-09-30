package com.example.currencyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.currencyproject.databinding.ActivityMainBinding
import com.example.currencyproject.main.MainViewModel
import com.example.currencyproject.utils.ConvertEvent
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val fromCurrency = binding.fromCarency.selectedItem as String
            val toCurrency = binding.toCarency.selectedItem as String
            viewModel.getConvertRate(
                fromCurrency.substring(0, 3),
                toCurrency.substring(0, 3),
                binding.amountET.text.toString()
            )
        }

        lifecycleScope.launchWhenCreated {
            viewModel.conversion.collectLatest {
                when(it){
                    ConvertEvent.Empty -> Unit
                    is ConvertEvent.Error -> {
                    binding.progressBar.isVisible = false
                    }
                    ConvertEvent.Loading -> binding.progressBar.isVisible = true
                    is ConvertEvent.Succes ->{
                        binding.progressBar.isVisible = false
                        binding.resultTV.text = "${getFormatted(it.result.result)}${it.result.query.to}"
                    }
                }
            }
        }

    }
    private fun getFormatted(amount:Double):String{
        return String.format("%.2f",amount)
    }
}