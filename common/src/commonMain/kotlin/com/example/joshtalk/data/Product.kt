package com.example.joshtalk.data

import kotlinx.serialization.Serializable


@Serializable
data class ProductResponse(
    val products: List<Product>
)

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val description: String
)