package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Data Classes for Dummy Content ---

data class Notification(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: String,
    val icon: ImageVector,
    val iconBgColor: Color
)

// --- Dummy Data ---

val orangeColor = Color(0xFFF9A825)
val greenColor = Color(0xFF4CAF50)

val dummyNotificationsToday = listOf(
    Notification(
        "1",
        "30% Special discount!",
        "Special discount for all food, only for today.",
        "10 min ago",
        Icons.Default.LocalOffer,
        orangeColor
    ),
    Notification(
        "2",
        "Order Successful!",
        "Your order #123456 has been successfully placed.",
        "30 min ago",
        Icons.Default.CheckCircle,
        greenColor
    ),
    Notification(
        "3",
        "Order Delivered!",
        "Your order #123456 has been delivered.",
        "1 hour ago",
        Icons.Default.CheckCircle,
        greenColor
    )
)

val dummyNotificationsEarlier = listOf(
    Notification(
        "4",
        "50% Special discount!",
        "Special discount for all drinks, only for yesterday.",
        "1 day ago",
        Icons.Default.LocalOffer,
        orangeColor
    ),
    Notification(
        "5",
        "Account Setup Successful!",
        "Your account has been created successfully.",
        "2 days ago",
        Icons.Default.CheckCircle,
        greenColor
    )
)

// --- Composable ---

@Composable
fun NotificationScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding() // Handle status bar
            .background(Color.White)
    ) {
        // Header
        item {
            Text(
                text = "Notification",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            )
        }

        // Today Section
        item {
            NotificationSectionHeader("Today")
        }
        items(dummyNotificationsToday) { notification ->
            NotificationItem(notification)
        }

        // Earlier Section
        item {
            NotificationSectionHeader("Earlier")
        }
        items(dummyNotificationsEarlier) { notification ->
            NotificationItem(notification)
        }
    }
}

@Composable
fun NotificationSectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    )
}

@Composable
fun NotificationItem(notification: Notification) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(notification.iconBgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = notification.icon,
                contentDescription = notification.title,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(Modifier.width(16.dp))

        // Text Content
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = notification.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = notification.description,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(Modifier.width(16.dp))

        // Timestamp
        Text(
            text = notification.timestamp,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

// --- Preview ---

@Preview(showBackground = true)
@Composable
fun PreviewNotificationScreen() {
    NotificationScreen()
}