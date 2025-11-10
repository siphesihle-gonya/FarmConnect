package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun UserRoleSelectionScreen(navController: NavController) {
    val orangeColor = Color(0xFFF9A825)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "How do you want to continue?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate(Routes.BuyerDashboard.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orangeColor)
            ) {
                Text("Continue as Buyer", fontSize = 16.sp, color = Color.White)
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate(Routes.SellerDashboard.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orangeColor)
            ) {
                Text("Continue as Seller", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserRoleSelectionScreen() {
    UserRoleSelectionScreen(rememberNavController())
}