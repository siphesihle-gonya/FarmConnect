package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
// Note: You will need to add your own icons for
// R.drawable.ic_visibility_on and R.drawable.ic_visibility_off
// I've used placeholder icons for this example.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(navController: NavController) {
    var newPassword by remember { mutableStateOf("............") }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("............") }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

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
        ) {
            // IconButton(onClick = { /* pop back stack */ }) {
            //     Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            // }
            Spacer(Modifier.height(40.dp))

            // Header
            Text(
                text = "Reset your password",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Please enter your new password and confirm the password",
                fontSize = 16.sp,
                color = lightTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left,
                lineHeight = 22.sp
            )

            Spacer(Modifier.height(32.dp))

            // New Password Field
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("New Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (newPasswordVisible)
                        painterResource(id = android.R.drawable.ic_menu_view) // Replace with R.drawable.ic_visibility_on
                    else
                        painterResource(id = android.R.drawable.ic_secure) // Replace with R.drawable.ic_visibility_off

                    IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                        Icon(painter = image, "Toggle password visibility")
                    }
                }
            )

            Spacer(Modifier.height(16.dp))

            // Confirm New Password Field
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm New Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (confirmPasswordVisible)
                        painterResource(id = android.R.drawable.ic_menu_view) // Replace with R.drawable.ic_visibility_on
                    else
                        painterResource(id = android.R.drawable.ic_secure) // Replace with R.drawable.ic_visibility_off

                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(painter = image, "Toggle password visibility")
                    }
                }
            )

            Spacer(Modifier.weight(1f)) // Pushes button to the bottom

            // Reset Password Button
            Button(
                onClick = { navController.navigate(Routes.PasswordSuccess.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orangeColor)
            ) {
                Text("Reset Password", fontSize = 16.sp, color = Color.White)
            }

            Spacer(Modifier.height(16.dp)) // Padding at the bottom
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewResetPasswordScreen() {
    ResetPasswordScreen(rememberNavController())
}