package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Note: You will need to add your own icons to the `res/drawable` folder
// for R.drawable.ic_google, R.drawable.ic_facebook, R.drawable.ic_apple
// and R.drawable.ic_visibility_off.
// I've used placeholder icons for this example.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("-") }
    var password by remember { mutableStateOf("-") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Define colors based on your desigN
    val orangeColor = Color(0xFFF9A825)
    val lightTextColor = Color.Gray
    val darkTextColor = Color.Black

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .systemBarsPadding(), // Handles status bar padding
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Spacer for status bar area (adjust as needed)
            Spacer(Modifier.height(40.dp))

            // Header
            Text(
                text = "Login to your account.",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Please sign in to your account",
                fontSize = 16.sp,
                color = lightTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left
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

            Spacer(Modifier.height(16.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        painterResource(id = android.R.drawable.ic_menu_view) // Replace with R.drawable.ic_visibility_on
                    else
                        painterResource(id = android.R.drawable.ic_menu_view) // Replace with R.drawable.ic_visibility_off

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = image, "Toggle password visibility")
                    }
                }
            )

            Spacer(Modifier.height(16.dp))

            // Forgot Password
            TextButton(
                onClick = { navController.navigate(Routes.ForgotPassword.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Forgot password?",
                    color = orangeColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(24.dp))

            // Sign In Button
            Button(
                onClick = { navController.navigate(Routes.UserRoleSelection.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orangeColor)
            ) {
                Text("Sign In", fontSize = 16.sp, color = Color.White)
            }

            Spacer(Modifier.height(32.dp))

            // "Or sign in with"
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
                Text(
                    text = "Or sign in with",
                    color = lightTextColor,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
            }

            Spacer(Modifier.height(32.dp))

            // Social Media Icons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // Replace with your actual icon buttons/images
                SocialLoginButton(R.drawable.ic_google, "Google") // Replace with R.drawable.ic_google
                Spacer(Modifier.width(24.dp))
                SocialLoginButton(R.drawable.ic_facebook, "Facebook") // Replace with R.drawable.ic_facebook
                Spacer(Modifier.width(24.dp))
                SocialLoginButton(R.drawable.ic_apple, "Apple") // Replace with R.drawable.ic_apple
            }

            Spacer(Modifier.weight(1f)) // Pushes content to the bottom

            // "Don't have an account?"
            TextButton(onClick = { navController.navigate(Routes.Register.route) }) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = lightTextColor)) {
                            append("Don\'t have an account? ")
                        }
                        withStyle(style = SpanStyle(color = orangeColor, fontWeight = FontWeight.Bold)) {
                            append("Register")
                        }
                    },
                    fontSize = 16.sp
                )
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}
