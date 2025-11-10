package com.example.myapplication

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductRepository {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun addProduct(product: Product) {
        _products.value = _products.value + product.copy(id = (_products.value.size + 1).toString())
    }

    fun updateProduct(product: Product) {
        _products.value = _products.value.map {
            if (it.id == product.id) product else it
        }
    }

    fun deleteProduct(productId: String) {
        _products.value = _products.value.filter { it.id != productId }
    }

    fun getProduct(productId: String): Product? {
        return _products.value.find { it.id == productId }
    }
}