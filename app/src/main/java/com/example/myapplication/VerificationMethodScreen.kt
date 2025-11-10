package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Note: You will need to add your own icons for
// R.drawable.ic_whatsapp and R.drawable.ic_email
// I've used placeholder icons for this example.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationMethodScreen() {

    // State to track the selected option
    var selectedMethod by remember { mutableStateOf("email") } // "email" or "whatsapp"

    // Define colors based on your design
    val orangeColor = Color(0xFFF9A825)
    val lightTextColor = Color.Gray
    val darkTextColor = Color.Black
    val cardBackgroundColor = Color.White
    val selectedBorderColor = orangeColor
    val unselectedBorderColor = Color.LightGray

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .systemBarsPadding(),
        ) {
            // Note: Your design has "Login to your account." which might be a copy-paste error.
            // I'm using a more context-appropriate title.
            // IconButton(onClick = { /* pop back stack */ }) {
            //     Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            // }
            Spacer(Modifier.height(40.dp))

            // Header
            Text(
                text = "Forgot password?", // More appropriate title
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Select which contact details should we use to reset your password",
                fontSize = 16.sp,
                color = lightTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left,
                lineHeight = 22.sp
            )

            Spacer(Modifier.height(32.dp))

            // WhatsApp Option Card
            VerificationOptionCard(
                title = "Send via Whatsapp",
                detail = "+27 72 343 5656",
                iconRes = android.R.drawable.stat_sys_speakerphone, // Replace with R.drawable.ic_whatsapp
                isSelected = selectedMethod == "whatsapp",
                onClick = { selectedMethod = "whatsapp" }
            )

            Spacer(Modifier.height(16.dp))

            // Email Option Card
            VerificationOptionCard(
                title = "Send via Email",
                detail = "Albertstevano@gmail.com",
                iconRes = android.R.drawable.ic_dialog_email, // Replace with R.drawable.ic_email
                isSelected = selectedMethod == "email",
                onClick = { selectedMethod = "email" }
            )

            Spacer(Modifier.weight(1f)) // Pushes button to the bottom

            // Continue Button
            Button(
                onClick = { /*TODO: Handle Continue, e.g., navigate to OTP screen*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orangeColor)
            ) {
                Text("Continue", fontSize = 16.sp, color = Color.White)
            }

            Spacer(Modifier.height(16.dp)) // Padding at the bottom
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationOptionCard(
    title: String,
    detail: String,
    iconRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val orangeColor = Color(0xFFF9A825)
    val borderColor = if (isSelected) orangeColor else Color.LightGray

    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(if (isSelected) 2.dp else 1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified // Use original icon colors if they are not just placeholders
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = detail,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = orangeColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVerificationMethodScreen() {
    VerificationMethodScreen()
}