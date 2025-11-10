package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel(private val orderRepository: OrderRepository) : ViewModel() {
    private val _cart = MutableStateFlow<List<Product>>(emptyList())
    val cart: StateFlow<List<Product>> = _cart

    val orders: StateFlow<List<Order>> = orderRepository.orders

    fun addToCart(product: Product) {
        _cart.value = _cart.value + product
    }

    fun removeFromCart(product: Product) {
        _cart.value = _cart.value - product
    }

    fun clearCart() {
        _cart.value = emptyList()
    }

    fun placeOrder(buyerName: String) {
        val order = Order(
            buyerName = buyerName,
            products = _cart.value
        )
        orderRepository.addOrder(order)
        clearCart()
    }
}