package com.example.myapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SellerOrdersScreen(cartViewModel: CartViewModel) {
    val orders by cartViewModel.orders.collectAsState()

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(orders) { order ->
                ListItem(
                    headlineContent = { Text("Order by ${order.buyerName}") },
                    supportingContent = { Text("Total: R${order.products.sumOf { it.price }}") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSellerOrdersScreen() {
    val orderRepository = OrderRepository()
    val cartViewModel = CartViewModel(orderRepository)
    orderRepository.addOrder(
        Order(
            buyerName = "Ronewa",
            products = listOf(
                Product(name = "Test Product", price = 10.0)
            )
        )
    )
    SellerOrdersScreen(cartViewModel)
}