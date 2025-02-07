package com.example.matulemain.data.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Favorite(
    val id: String?,
    val user_id: String,
    val product_id: String
)