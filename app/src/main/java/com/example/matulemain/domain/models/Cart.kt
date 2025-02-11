package com.example.matulemain.domain.models

data class Cart (
    val id: String? = null,
    val user_id: String?,
    val product_id: String?,
    val quantity: Int?
)