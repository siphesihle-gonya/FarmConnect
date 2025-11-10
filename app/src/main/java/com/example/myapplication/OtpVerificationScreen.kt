package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpVerificationScreen(navController: NavController) {

    // Holds the 6 digits of the OTP
    var otpValue by remember { mutableStateOf(List(6) { "" }) }
    // Timer state
    var timerSeconds by remember { mutableStateOf(59) }

    // Define colors based on your design
    val orangeColor = Color(0xFFF9A825)
    val lightTextColor = Color.Gray
    val darkTextColor = Color.Black

    // Start a countdown timer
    LaunchedEffect(key1 = timerSeconds) {
        if (timerSeconds > 0) {
            delay(1000L)
            timerSeconds--
        }
    }

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
                text = "OTP Code Verification",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "We have sent an OTP code to your email. Please check your inbox.",
                fontSize = 16.sp,
                color = lightTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left,
                lineHeight = 22.sp
            )

            Spacer(Modifier.height(32.dp))

            // OTP Input Fields
            OtpInputRow(
                otpValue = otpValue,
                onValueChange = { newOtpValue ->
                    otpValue = newOtpValue
                }
            )

            Spacer(Modifier.height(32.dp))

            // Resend Code Timer
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = darkTextColor)) {
                        append("Resend code in ")
                    }
                    withStyle(style = SpanStyle(color = orangeColor, fontWeight = FontWeight.Bold)) {
                        append("00:${timerSeconds.toString().padStart(2, '0')}")
                    }
                },
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.weight(1f)) // Pushes button to the bottom

            // Verify Button
            Button(
                onClick = { navController.navigate(Routes.ResetPassword.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orangeColor),
                // Disable button until all 6 digits are filled
                enabled = otpValue.all { it.isNotEmpty() }
            ) {
                Text("Verify", fontSize = 16.sp, color = Color.White)
            }

            Spacer(Modifier.height(16.dp)) // Padding at the bottom
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpInputRow(
    otpValue: List<String>,
    onValueChange: (List<String>) -> Unit
) {
    val focusRequesters = remember { List(6) { FocusRequester() } }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        otpValue.forEachIndexed { index, value ->
            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        val newOtpList = otpValue.toMutableList()
                        newOtpList[index] = newValue
                        onValueChange(newOtpList)

                        // Move focus to next box
                        if (newValue.isNotEmpty() && index < 5) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    }
                },
                modifier = Modifier
                    .size(50.dp)
                    .focusRequester(focusRequesters[index])
                    .onKeyEvent {
                        // Handle backspace to move focus backward
                        if (it.key == Key.Backspace && value.isEmpty() && index > 0) {
                            focusRequesters[index - 1].requestFocus()
                            true
                        } else {
                            false
                        }
                    },
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                maxLines = 1
            )
        }
    }

    // Request focus on the first empty field when composable appears
    LaunchedEffect(Unit) {
        focusRequesters.firstOrNull()?.requestFocus()
        keyboardController?.show()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOtpVerificationScreen() {
    OtpVerificationScreen(rememberNavController())
}