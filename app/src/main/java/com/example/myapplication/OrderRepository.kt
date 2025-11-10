package com.example.myapplication

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OrderRepository {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    fun addOrder(order: Order) {
        _orders.value = _orders.value + order.copy(id = (_orders.value.size + 1).toString())
    }
}