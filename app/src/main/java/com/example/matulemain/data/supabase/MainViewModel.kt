package com.example.matulemain.data.supabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulemain.data.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(val baseManager: BaseManager) : ViewModel() {

    val listOfProducts = MutableStateFlow(listOf<Product>())

    fun getProducts() = viewModelScope.launch{

        val prod = baseManager.getProducts()

        listOfProducts.update {
            prod
        }
    }

}