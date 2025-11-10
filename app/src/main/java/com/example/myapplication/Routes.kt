package com.example.myapplication

sealed class Routes(val route: String) {
    // Onboarding & Auth Flow
    object Onboarding : Routes("onboarding")
    object GetStarted : Routes("get_started")
    object Login : Routes("login")
    object Register : Routes("register")
    object UserRoleSelection : Routes("user_role_selection")

    // Forgot Password Flow
    object ForgotPassword : Routes("forgot_password")
    object VerificationMethod : Routes("verification_method")
    object OtpVerification : Routes("otp_verification")
    object ResetPassword : Routes("reset_password")
    object PasswordSuccess : Routes("password_success")

    // Main App (with Bottom Nav)
    object BuyerDashboard : Routes("buyer_dashboard")
    object SellerDashboard : Routes("seller_dashboard")
    object Cart : Routes("cart")
    object OrderSuccess : Routes("order_success")

    // We point to the MainAppScreen, which has its own internal navigation.
}