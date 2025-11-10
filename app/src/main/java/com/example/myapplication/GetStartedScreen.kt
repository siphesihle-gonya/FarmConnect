package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Note: You will need to add your full-screen background image to
// `res/drawable` as R.drawable.onboarding_background

@Composable
fun GetStartedScreen(navController: NavController) {

    // Define colors based on your design
    val orangeColor = Color(0xFFF9A825)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding() // Handles status/navigation bars
    ) {
        // Full-screen Background Image
        Image(
            painter = painterResource(id = R.drawable.onboarding_5),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Fills the entire screen
        )

        // Column for the buttons, aligned to the bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 40.dp), // Add padding
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Button(
                onClick = { navController.navigate(Routes.Login.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orangeColor)
            ) {
                Text("Login", fontSize = 16.sp, color = Color.White)
            }

            Spacer(Modifier.height(16.dp))


            OutlinedButton(
                onClick = { navController.navigate(Routes.Register.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = orangeColor // Text color
                ),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(
                    width = 2.dp,
                    brush = androidx.compose.ui.graphics.SolidColor(orangeColor) // Border color
                )
            ) {
                Text("Register", fontSize = 16.sp, color = orangeColor)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGetStartedScreen() {
    GetStartedScreen(rememberNavController())
}