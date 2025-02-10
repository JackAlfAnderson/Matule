package com.example.matulemain.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Favorite(
    val id: String? = null,
    val user_id: String,
    val product_id: String
)