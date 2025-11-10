package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val products: StateFlow<List<Product>> = productRepository.products
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addProduct(product: Product) {
        productRepository.addProduct(product)
    }

    fun updateProduct(product: Product) {
        productRepository.updateProduct(product)
    }

    fun deleteProduct(productId: String) {
        productRepository.deleteProduct(productId)
    }

    fun getProduct(productId: String): Product? {
        return productRepository.getProduct(productId)
    }
}