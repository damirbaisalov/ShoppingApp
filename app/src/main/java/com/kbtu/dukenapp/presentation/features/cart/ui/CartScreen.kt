package com.kbtu.dukenapp.presentation.features.cart.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kbtu.dukenapp.presentation.features.cart.mvi.Intent
import com.kbtu.dukenapp.presentation.features.cart.mvi.State
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.ui.theme.LightBlueBackground
import com.kbtu.dukenapp.ui.theme.black

@Composable
fun CartScreen(
    state: State,
    performIntent: (Intent) -> Unit
) {
    Scaffold(
        topBar = {
//            TopAppBar(
//                title = { Text("Shopping Cart", style = MaterialTheme.typography.h6) },
//                backgroundColor = MaterialTheme.colors.primarySurface,
//                actions = {
//                    IconButton(onClick = { /* Handle Cart Actions */ }) {
//                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart")
//                    }
//                }
//            )
        },
        containerColor = LightBlueBackground,
        contentColor = LightBlueBackground,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (state.cart.isEmpty()) {
                EmptyCartView()
            } else {
                CartItemList(
                    cartItems = state.cart,
                    onIncreaseQuantity = { product ->
                        performIntent(
                            Intent.OnIncreaseQuantity(
                                product
                            )
                        )
                    },
                    onDecreaseQuantity = { product ->
                        performIntent(
                            Intent.OnDecreaseQuantity(
                                product
                            )
                        )
                    },
                    onRemoveFromCart = { product ->
                        performIntent(
                            Intent.OnRemoveFromCartClick(
                                product
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                TotalAmount(cartItems = state.cart)
                ProceedToCheckoutButton(onCheckoutClick = { performIntent(Intent.OnCheckoutClick) })
            }
        }
    }
}

@Composable
fun EmptyCartView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Empty Cart",
            modifier = Modifier.size(120.dp),
            tint = Color.Gray
        )
        Text(
            text = "Your cart is empty",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun CartItemList(
    cartItems: List<ProductUiModel>,
    onIncreaseQuantity: (ProductUiModel) -> Unit,
    onDecreaseQuantity: (ProductUiModel) -> Unit,
    onRemoveFromCart: (ProductUiModel) -> Unit
) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        items(cartItems) { product ->
            CartItemRow(
                product = product,
                onIncreaseQuantity = onIncreaseQuantity,
                onDecreaseQuantity = onDecreaseQuantity,
                onRemoveFromCart = onRemoveFromCart
            )
        }
    }
}

@Composable
fun CartItemRow(
    product: ProductUiModel,
    onIncreaseQuantity: (ProductUiModel) -> Unit,
    onDecreaseQuantity: (ProductUiModel) -> Unit,
    onRemoveFromCart: (ProductUiModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.images.first()),
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onDecreaseQuantity(product) }) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease Quantity",
                        tint = Color.Gray
                    )
                }
                Text(
                    text = "${product.count}",
                    style = MaterialTheme.typography.bodySmall
                )
                IconButton(onClick = { onIncreaseQuantity(product) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase Quantity",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = { onRemoveFromCart(product) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove from Cart",
                    tint = Color.Red
                )
            }
        }
    }
}

@Composable
fun TotalAmount(cartItems: List<ProductUiModel>) {
    val totalAmount = cartItems.sumOf { it.price * it.count }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Total:",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
        Text(
            text = "$${"%.2f".format(totalAmount)}",
            style = MaterialTheme.typography.bodyLarge,
            color = black
        )
    }
}

@Composable
fun ProceedToCheckoutButton(onCheckoutClick: () -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = onCheckoutClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = "Proceed to Checkout")
    }
}


