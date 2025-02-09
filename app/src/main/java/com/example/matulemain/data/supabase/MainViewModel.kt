package com.example.matulemain.data.supabase

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulemain.data.domain.models.Favorite
import com.example.matulemain.data.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(val baseManager: BaseManager) : ViewModel() {

    val listOfProducts = MutableStateFlow(listOf<Product>())
    val isShow = MutableStateFlow(false)
    val listOfFavorites = MutableStateFlow(listOf<Favorite>())

    fun getProducts() = viewModelScope.launch{

        isShow.update {
            true
        }
        Log.d("prod", "я запустился два")
        val prod = baseManager.getProducts()
        Log.d("prod", "я запустился пять")


        listOfProducts.update {
            prod
        }

        isShow.update {
            false
        }
    }

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch{
        baseManager.insertFavorite(favorite)
    }

    fun deleteFavorite(userId: String, productId: String) = viewModelScope.launch {
        baseManager.deleteFavorite(userId,productId)
    }

    fun getFavoriteList(userId: String) = viewModelScope.launch {
        listOfFavorites.update {
            baseManager.getFavoriteList(userId)
        }

    }
}