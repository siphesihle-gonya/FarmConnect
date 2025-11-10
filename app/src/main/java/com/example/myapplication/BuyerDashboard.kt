package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

sealed class BuyerBottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BuyerBottomNavItem("buyer_home", Icons.Default.Home, "Home")
    object Orders : BuyerBottomNavItem("buyer_orders", Icons.AutoMirrored.Filled.List, "Orders")
    object Profile : BuyerBottomNavItem("buyer_profile", Icons.Default.Person, "Profile")
}

val buyerNavItems = listOf(
    BuyerBottomNavItem.Home,
    BuyerBottomNavItem.Orders,
    BuyerBottomNavItem.Profile
)

@Composable
fun BuyerDashboard(productViewModel: ProductViewModel, cartViewModel: CartViewModel, navController: NavController) {
    var selectedTab by remember { mutableStateOf<BuyerBottomNavItem>(BuyerBottomNavItem.Home) }
    val orangeColor = Color(0xFFF9A825)

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.Gray,
                tonalElevation = 8.dp
            ) {
                buyerNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = selectedTab == item,
                        onClick = { selectedTab = item },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = orangeColor,
                            selectedTextColor = orangeColor,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.White
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                BuyerBottomNavItem.Home -> BuyerHomeScreen(productViewModel, cartViewModel, navController)
                BuyerBottomNavItem.Orders -> BuyerOrdersScreen()
                BuyerBottomNavItem.Profile -> BuyerProfileScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBuyerDashboard() {
    BuyerDashboard(ProductViewModel(ProductRepository()), CartViewModel(OrderRepository()), rememberNavController())
}