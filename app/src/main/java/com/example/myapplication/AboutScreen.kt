package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

// Note: You will need to add your own app logo to
// `res/drawable` as R.drawable.app_logo

@Composable
fun AboutScreen() {

    // Define colors
    val orangeColor = Color(0xFFF9A825)
    val lightTextColor = Color.Gray
    val darkTextColor = Color.Black

    // Lorem Ipsum placeholder text
    val aboutText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
            "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
            "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
            "officia deserunt mollit anim id est laborum."

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding() // Handle status bar
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "About",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        )

        // Spacer to push content down a bit
        Spacer(Modifier.height(32.dp))

        // App Logo
        Image(
            painter = painterResource(id = android.R.drawable.ic_dialog_map), // Replace with R.drawable.app_logo
            contentDescription = "App Logo",
            modifier = Modifier
                .size(120.dp)
        )

        Spacer(Modifier.height(16.dp))

        // App Name
        Text(
            text = "Flid",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = darkTextColor
        )

        // App Version
        Text(
            text = "Version 1.0.0",
            fontSize = 14.sp,
            color = lightTextColor
        )

        Spacer(Modifier.height(24.dp))

        // App Description
        Text(
            text = aboutText,
            fontSize = 14.sp,
            color = lightTextColor,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )

        // Spacer to push button to the bottom
        Spacer(Modifier.weight(1f))

        // Share this app Button
        OutlinedButton(
            onClick = { /*TODO: Handle Share App*/ },
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
            Text("Share this app", fontSize = 16.sp, color = orangeColor)
        }

        // Bottom padding
        Spacer(Modifier.height(16.dp))
    }
}

// --- Preview ---

@Preview(showBackground = true)
@Composable
fun PreviewAboutScreen() {
    AboutScreen()
}