package com.example.matulemain.data.supabase

import android.util.Log
import com.example.matulemain.data.domain.models.Favorite
import com.example.matulemain.data.domain.models.Product
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class BaseManager(val supabaseClient: SupabaseClient) {

    suspend fun getProducts(): List<Product> {
        Log.d("prod", "я запустился три")

        val prod = supabaseClient.postgrest["products"].select().decodeList<Product>()

        Log.d("prod", "я запустился четыре")


        return prod
    }

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