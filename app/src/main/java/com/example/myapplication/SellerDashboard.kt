package com.example.myapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class SellerBottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Products : SellerBottomNavItem("seller_products", Icons.Default.AddBusiness, "Products")
    object Orders : SellerBottomNavItem("seller_orders", Icons.AutoMirrored.Filled.List, "Orders")
    object Profile : SellerBottomNavItem("seller_profile", Icons.Default.Person, "Profile")
}

val sellerNavItems = listOf(
    SellerBottomNavItem.Products,
    SellerBottomNavItem.Orders,
    SellerBottomNavItem.Profile
)

@Composable
fun SellerDashboard(productViewModel: ProductViewModel, cartViewModel: CartViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            SellerBottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SellerBottomNavItem.Products.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(SellerBottomNavItem.Products.route) {
                SellerProductsScreen(navController, productViewModel)
            }
            composable(SellerBottomNavItem.Orders.route) {
                SellerOrdersScreen(cartViewModel)
            }
            composable(SellerBottomNavItem.Profile.route) {
                SellerProfileScreen()
            }
            composable("add_product") {
                AddProductScreen(navController, productViewModel)
            }
            composable("edit_product/{productId}") { backStackEntry ->
                EditProductScreen(
                    navController = navController,
                    productViewModel = productViewModel,
                    productId = backStackEntry.arguments?.getString("productId")
                )
            }
        }
    }
}

@Composable
private fun SellerBottomNavigationBar(navController: NavHostController) {
    val orangeColor = Color(0xFFF9A825)
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Gray,
        tonalElevation = 8.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        sellerNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { 
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.startDestinationId)
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                    }
                },
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

@Preview(showBackground = true)
@Composable
fun PreviewSellerDashboard() {
    SellerDashboard(ProductViewModel(ProductRepository()), CartViewModel(OrderRepository()))
}