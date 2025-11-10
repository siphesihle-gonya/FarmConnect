package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val productRepository = remember { ProductRepository() }
    val productViewModel: ProductViewModel = viewModel(factory = ViewModelFactory(productRepository))
    val orderRepository = remember { OrderRepository() }
    val cartViewModel: CartViewModel = viewModel(factory = CartViewModelFactory(orderRepository))

    NavHost(navController = navController, startDestination = Routes.Onboarding.route) {
        composable(Routes.Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(Routes.GetStarted.route) {
            GetStartedScreen(navController)
        }
        composable(Routes.Login.route) {
            LoginScreen(navController)
        }
        composable(Routes.Register.route) {
            RegisterScreen(navController)
        }
        composable(Routes.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
        composable(Routes.OtpVerification.route) {
            OtpVerificationScreen(navController)
        }
        composable(Routes.ResetPassword.route) {
            ResetPasswordScreen(navController)
        }
        composable(Routes.PasswordSuccess.route) {
            PasswordSuccessScreen(navController)
        }
        composable(Routes.UserRoleSelection.route) {
            UserRoleSelectionScreen(navController)
        }
        composable(Routes.BuyerDashboard.route) {
            BuyerDashboard(productViewModel, cartViewModel, navController)
        }
        composable(Routes.SellerDashboard.route) {
            SellerDashboard(productViewModel, cartViewModel)
        }
        composable(Routes.Cart.route) {
            CartScreen(navController, cartViewModel)
        }
        composable(Routes.OrderSuccess.route) {
            OrderSuccessScreen(navController)
        }
    }
}