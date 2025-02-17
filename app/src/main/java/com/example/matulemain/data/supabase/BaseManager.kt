package com.example.matulemain.data.supabase

import com.example.matulemain.domain.models.Cart
import com.example.matulemain.domain.models.Favorite
import com.example.matulemain.domain.models.Product
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class BaseManager(val supabaseClient: SupabaseClient) {


    //CART

    suspend fun getCartProductsList(userId: String): List<Product> {
        val cartList = supabaseClient.postgrest["cart"].select {
            filter {
                eq("user_id", userId)
            }
        }.decodeList<Cart>()

        val productList = mutableListOf<Product>()

        cartList.forEach {
                productList.add(supabaseClient.postgrest["products"].select {
                    filter {
                        eq("id", it.product_id.toString())
                    }
                }.decodeSingle()
            )
        }

        return productList
    }

    suspend fun getCartList(userId: String): List<Cart> {
        val cartList = supabaseClient.postgrest["cart"].select {
            filter {
                eq("user_id", userId)
            }
        }.decodeList<Cart>()

        return cartList
    }

    suspend fun getQuantity(userId: String, productId: String) : Int{
        val cart = supabaseClient.postgrest["cart"].select {
            filter {
                eq("user_id",userId)
                eq("product_id", productId)
            }
        }.decodeSingle<Cart>()

        return cart.quantity!!
    }

    suspend fun updateQuantity(userId: String, productId: String, newQuantity: Int) {
        supabaseClient.postgrest["cart"].update(
            {
                set("quantity", newQuantity)
            }
        ) {
            filter {
                eq("user_id",userId)
                eq("product_id", productId)
            }
        }
    }

    suspend fun insertCart(cart: Cart) {
        supabaseClient.postgrest["cart"].insert(cart)
    }

    suspend fun deleteCart(cart: Cart) {
        supabaseClient.postgrest["cart"].delete {
            filter {
                eq("user_id", cart.user_id.toString())
                eq("product_id", cart.product_id.toString())
            }
        }
    }


    //PRODUCTS
    suspend fun getProducts(): List<Product> {
        val prod = supabaseClient.postgrest["products"].select().decodeList<Product>()
        return prod
    }

    //FAVORITE
    suspend fun insertFavorite(favorite: Favorite) {
        supabaseClient.postgrest["favorites"].insert(favorite)
    }

    suspend fun deleteFavorite(userId: String, productId: String) {
        supabaseClient.postgrest["favorites"].delete {
            filter {
                eq("user_id", userId)
                eq("product_id", productId)
            }
        }
    }

    suspend fun getFavoriteList(userId: String): List<Product> {
        val listOfFav = supabaseClient.postgrest["favorites"].select {
            filter {
                eq("user_id", userId)
            }
        }.decodeList<Favorite>()

        val listOfFavProd = mutableListOf<Product>()

        listOfFav.forEach {
            listOfFavProd.add(
                supabaseClient.postgrest["products"].select {
                    filter {
                        eq("id", it.product_id)
                    }
                }.decodeSingle<Product>()
            )
        }


        return listOfFavProd
    }
}