package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("Albertstevano@gmail.com") }

    // Define colors based on your design
    val orangeColor = Color(0xFFF9A825) // Example Orange - adjust to your design
    val lightTextColor = Color.Gray
    val darkTextColor = Color.Black

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .systemBarsPadding(), // Handles status bar padding
        ) {
            // Note: Your design doesn't show a back arrow here.
            // If this is a full-screen modal, you might add one:
            // IconButton(onClick = { /* pop back stack */ }) {
            //     Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            // }

            Spacer(Modifier.height(40.dp))

            // Header
            Text(
                text = "Forgot password?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Enter your email address and we'll send you confirmation code to reset your password",
                fontSize = 16.sp,
                color = lightTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left,
                lineHeight = 22.sp // For better readability
            )

            Spacer(Modifier.height(32.dp))

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            Spacer(Modifier.height(32.dp))

            // Continue Button
            Button(
                onClick = { navController.navigate(Routes.VerificationMethod.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orangeColor)
            ) {
                Text("Continue", fontSize = 16.sp, color = Color.White)
            }

            // Spacer to push content to the top
            Spacer(Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForgotPasswordScreen() {
    ForgotPasswordScreen(rememberNavController())
}