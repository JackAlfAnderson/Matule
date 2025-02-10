package com.example.matulemain.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Product (
    val id: String?,
    val name:String?,
    val category:String?,
    val description:String?,
    val price:Double?,
    val best_seller: Boolean?,
    val image: String?,
    val created_at: String?
)