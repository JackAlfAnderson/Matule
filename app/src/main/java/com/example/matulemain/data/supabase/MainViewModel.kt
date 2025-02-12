package com.example.matulemain.data.supabase

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulemain.domain.models.Cart
import com.example.matulemain.domain.models.Favorite
import com.example.matulemain.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(val baseManager: BaseManager) : ViewModel() {

    //APP VALUES

    val listOfProducts = MutableStateFlow(listOf<Product>())
    val isShow = MutableStateFlow(false)
    val listOfFavorites = MutableStateFlow(listOf<Product>())
    val listOfCart = MutableStateFlow(listOf<Product>())
    val foundCart = MutableStateFlow(Cart(null,null,null,null))
    val listOfFoundCart = MutableStateFlow(listOf<Cart>())


    //PRODUCTS
    fun getProducts() = viewModelScope.launch{

        isShow.update {
            true
        }
        val prod = baseManager.getProducts()
        listOfProducts.update {
            prod
        }

        isShow.update {
            false
        }
    }

    //CART

    fun getCartList(userId: String) = viewModelScope.launch {
        listOfCart.update {
            baseManager.getCartList(userId)

        }

    }

    fun findCartById(cart: Cart) = viewModelScope.launch{
        foundCart.update {
            baseManager.getCartById(cart)
        }
    }

    fun insertCart(cart: Cart) = viewModelScope.launch {
        baseManager.insertCart(cart)
    }

    fun deleteCart(cart: Cart) = viewModelScope.launch {
        baseManager.deleteCart(cart)
    }

    fun setQuantity(cart: Cart) = viewModelScope.launch {
        baseManager.setQuantity(cart)
    }

    //FAVORITE

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