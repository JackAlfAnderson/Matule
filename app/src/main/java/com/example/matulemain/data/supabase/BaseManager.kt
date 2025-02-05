package com.example.matulemain.data.supabase

import com.example.matulemain.data.domain.models.Product
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class BaseManager(val supabaseClient: SupabaseClient) {

    suspend fun getProducts(): List<Product>{
        val prod = supabaseClient.postgrest["products"].select().decodeList<Product>()

        return prod
    }

}