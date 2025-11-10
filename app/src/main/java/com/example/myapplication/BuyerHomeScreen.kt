package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* 
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter

// --- Data Classes for Dummy Content ---

data class Category(
    val id: String,
    val name: String,
    val iconRes: Int // Using Int for drawable resource IDs
)

// --- Dummy Data ---

val dummyCategories = listOf(

    Category("1", "Carrots", R.drawable.ic_carrot),
    Category("2", "Peppers", R.drawable.ic_peppers),
    Category("3", "Tomatoes", R.drawable.ic_tomatoes),
    Category("4", "Onions", R.drawable.ic_onions),
    Category("5", "Beetroots", R.drawable.ic_beetroots), // Assuming you named it ic_spinach
    Category("6", "Cabbages", R.drawable.ic_garlic),
)


@Composable
fun BuyerHomeScreen(
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    navController: NavController
) {
    val products by productViewModel.products.collectAsState()

    // Main scrollable column for the screen content
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding() // Handle status bar
            .padding(bottom = 16.dp) // Add padding so content doesn't hide behind nav bar
    ) {
        // Header
        item { HomeHeader(navController) }

        // Search Bar
        item { SearchBar() }

        // Special Offer
        item { SpecialOfferSection() }

        // Categories
        item {
            HomeSection(
                title = "Categories",
                onSeeAllClick = { /* TODO */ }
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(dummyCategories) { category ->
                        CategoryItem(category)
                    }
                }
            }
        }

        // Recommended
        item {
            HomeSection(
                title = "Products",
                onSeeAllClick = { /* TODO */ }
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(products) { product ->
                        ProductItemCard(product, onAddToCart = { cartViewModel.addToCart(product) })
                    }
                }
            }
        }
    }
}

@Composable
fun HomeHeader(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Hi, Ronewa",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Braamfontien, Johannesburg",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        IconButton(onClick = { navController.navigate(Routes.Cart.route) }) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Search for food") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F5F5), // Light gray background
            unfocusedContainerColor = Color(0xFFF5F5F5),
            disabledContainerColor = Color(0xFFF5F5F5),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SpecialOfferSection() {

    Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.homescreen_1),
                contentDescription = "Special Offer",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun HomeSection(
    title: String,
    onSeeAllClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            TextButton(onClick = onSeeAllClick) {
                Text(
                    text = "See All",
                    color = Color(0xFFF9A825), // Orange color
                    fontWeight = FontWeight.Bold
                )
            }
        }
        content()
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(60.dp)
            .clickable { /* TODO: Handle category click */ }
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5)), // Light gray background
            contentAlignment = Alignment.Center
        ) {
            // --- CHANGE THIS ---
            Image( // Use Image instead of Icon
                painter = painterResource(id = category.iconRes),
                contentDescription = category.name,
                modifier = Modifier.size(30.dp)
                // No tint is needed here
            )
            // --- END CHANGE ---
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = category.name,
            fontSize = 12.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ProductItemCard(item: Product, onAddToCart: () -> Unit) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .clickable { /* TODO: Handle item click */ },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = item.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "R${item.price}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF9A825) // Orange color
                    )
                    Button(
                        onClick = onAddToCart,
                    ) {
                        Text("Add")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBuyerHomeScreen() {
     BuyerHomeScreen(ProductViewModel(ProductRepository()), CartViewModel(OrderRepository()), rememberNavController())
}