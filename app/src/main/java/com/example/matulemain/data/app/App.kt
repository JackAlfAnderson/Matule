package com.example.matulemain.data.app

import android.app.Application
import com.example.matulemain.domain.models.Favorite
import com.example.matulemain.domain.models.Product
import com.example.matulemain.data.supabase.BaseManager
import com.example.matulemain.domain.models.Cart
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

class App() : Application() {

    companion object{
        lateinit var instance: App
            private set
        var chosenCategory = 0
        var userId = "1c209d33-174e-434c-a4ca-4e46ef8fe855"
        var listOfFavorite = mutableListOf<Favorite>()
        var listOfCart = mutableListOf<Cart>()
        var activeIcon = 1
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private val supabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = "https://jxdsyxjacclupdsirxed.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp4ZHN5eGphY2NsdXBkc2lyeGVkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzM0NzM3MDQsImV4cCI6MjA0OTA0OTcwNH0.-6d1GMiZkMyaUkRMWk0EfZLxeXKGKEa-UNarFphKXVo"
        ){
            install(Postgrest)
            install(Auth)
            install(Storage)
        }
    }

    val baseManager by lazy {
        BaseManager(supabaseClient)
    }

}