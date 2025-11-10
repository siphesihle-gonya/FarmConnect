package com.example.myapplication

data class Order(
    val id: String = "",
    val buyerName: String = "", // In a real app, this would be a user ID
    val products: List<Product> = emptyList(),
    val timestamp: Long = System.currentTimeMillis()
)