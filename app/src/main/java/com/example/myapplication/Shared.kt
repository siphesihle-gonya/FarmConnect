package com.example.myapplication

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SocialLoginButton(iconRes: Int, contentDescription: String) {
    OutlinedIconButton(
        onClick = { /*TODO: Handle social login*/ },
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified // Use original icon colors
        )
    }
}