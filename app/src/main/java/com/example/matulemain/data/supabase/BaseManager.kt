package com.example.matulemain.data.supabase

import android.util.Log
import com.example.matulemain.data.domain.models.Favorite
import com.example.matulemain.data.domain.models.Product
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class BaseManager(val supabaseClient: SupabaseClient) {

    suspend fun getProducts(): List<Product>{
        Log.d("prod", "я запустился три")

        val prod = supabaseClient.postgrest["products"].select().decodeList<Product>()

        Log.d("prod", "я запустился четыре")


        return prod
    }

    suspend fun insertFavorite(favorite: Favorite){
        supabaseClient.postgrest["favorite"].insert(favorite)
    }

}