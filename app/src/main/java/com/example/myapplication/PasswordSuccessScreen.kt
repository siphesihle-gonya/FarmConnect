package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Note: You will need to add your own icon for
// R.drawable.ic_success_checkmark
// I've used a placeholder icon for this example.

@Composable
fun PasswordSuccessScreen(navController: NavController) {

    // Define colors based on your design
    val orangeColor = Color(0xFFF9A825)
    val lightTextColor = Color.Gray
    val darkTextColor = Color.Black

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Center all content vertically
        ) {

            // Success Icon
            Icon(
                painter = painterResource(id = android.R.drawable.ic_dialog_map), // Replace with R.drawable.ic_success_checkmark
                contentDescription = "Success",
                tint = orangeColor, // Assuming the icon should be tinted
                modifier = Modifier.size(100.dp)
            )

            Spacer(Modifier.height(32.dp))

            // Header
            Text(
                text = "Success!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "You have successfully reset your password. Please use your new password to sign in.",
                fontSize = 16.sp,
                color = lightTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(Modifier.height(48.dp))

            // Sign In Button
            Button(
                onClick = { navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                } },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orangeColor)
            ) {
                Text("Sign In", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordSuccessScreen() {
    PasswordSuccessScreen(rememberNavController())
}