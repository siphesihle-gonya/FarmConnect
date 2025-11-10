package com.example.myapplication

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

// Note: You will need to add your own images to `res/drawable`:
// R.drawable.onboarding_image_1
// R.drawable.onboarding_image_2
// R.drawable.onboarding_image_3

// Define a data class for our onboarding page content
data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)

// Create a list of the pages
val onboardingPages = listOf(
    OnboardingPage(
        imageRes = R.drawable.onboarding_2,
        title = "FarmConnect",
        description = "You don't have to go far to find a good fruits & vegitables, we have provided all the fruits and veggies that are near you"
    ),
    OnboardingPage(
        imageRes = R.drawable.onboarding_3,
        title = "Order Fast, Eat Fresh",
        description = "Select your favorite Veggies and get notifications about menu updates, discounts, and more"
    ),
    OnboardingPage(
        imageRes = R.drawable.onboarding_4,
        title = "Transform Your Meal Experience",
        description = "Find your favorite food at a cheap price and get it delivered to your doorstep"
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val coroutineScope = rememberCoroutineScope()

    // Define colors
    val orangeColor = Color(0xFFF9A825)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // "Skip" Button
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)) {
                TextButton(
                    onClick = { navController.navigate(Routes.GetStarted.route) },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text("Skip", color = Color.Gray, fontSize = 16.sp)
                }
            }

            // Horizontal Pager (for the images and text)
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f) // Takes up most of the screen
            ) { page ->
                OnboardingPageContent(page = onboardingPages[page])
            }

            // Page Indicator (Dots)
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = orangeColor,
                inactiveColor = Color(0xFFFDECCF) // Light orange from design
            )

            Spacer(Modifier.height(48.dp))

            // Next / Get Started Button
            Button(
                onClick = {
                    if (pagerState.currentPage < onboardingPages.size - 1) {
                        // Go to next page
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        // Last page: Handle "Get Started"
                        navController.navigate(Routes.GetStarted.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orangeColor)
            ) {
                Text(
                    text = if (pagerState.currentPage == onboardingPages.size - 1) "Get Started" else "Next",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            Spacer(Modifier.height(40.dp))
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = page.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f), // Makes it roughly square
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(48.dp))
        Text(
            text = page.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = page.description,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    activeColor: Color,
    inactiveColor: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) activeColor else inactiveColor
            val width = animateDpAsState(
                targetValue = if (pagerState.currentPage == iteration) 24.dp else 8.dp,
                label = "width animation"
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(width = width.value, height = 8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen(rememberNavController())
}