package com.kbtu.dukenapp.presentation.features.cart.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kbtu.dukenapp.presentation.features.cart.mvi.Effect
import com.kbtu.dukenapp.presentation.features.cart.mvi.Intent
import com.kbtu.dukenapp.presentation.features.cart.mvi.State
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.ui.theme.LightBlueBackground
import com.kbtu.dukenapp.ui.theme.black
import com.kbtu.dukenapp.utils.extension.CollectAsSideEffect
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun CartScreen(
    state: State,
    performIntent: (Intent) -> Unit,
    sideEffect: SharedFlow<Effect>
) {
    val context = LocalContext.current
    sideEffect.CollectAsSideEffect {
        when (it) {
            is Effect.ShowSuccessToast -> {
                Toast.makeText(context, "Successfully created order", Toast.LENGTH_SHORT).show()
            }
            is Effect.ShowErrorToast -> {
                Toast.makeText(context, "You have to Sign In to create order", Toast.LENGTH_SHORT).show()

            }
        }
    }
    Scaffold(
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
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(state.cart) { product ->
                        CartItemRow (
                            product = product,
                            onIncreaseQuantity = {
                                performIntent(Intent.OnIncreaseQuantity(product))
                            },
                            onDecreaseQuantity = {
                                performIntent(Intent.OnDecreaseQuantity(product))
                            },
                            onRemoveFromCart = {
                                performIntent(Intent.OnRemoveFromCartClick(product))
                            },
                            onProductClick = {
                                performIntent(Intent.OnProductClick(it))
                            }
                        )
                    }
                }
                TotalAmount(totalPrice = state.totalPrice)
                CreateOrderButton(
                    hasItemsInCart = state.cart.isNotEmpty(),
                    onCheckoutClick = { performIntent(Intent.OnCheckoutClick) }
                )
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
fun CartItemRow(
    product: ProductUiModel,
    onIncreaseQuantity: (ProductUiModel) -> Unit,
    onDecreaseQuantity: (ProductUiModel) -> Unit,
    onRemoveFromCart: (ProductUiModel) -> Unit,
    onProductClick: (Int) -> Unit
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
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onProductClick(product.productId) },
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))


            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
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
    }
}

@Composable
fun TotalAmount(totalPrice: Double) {
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
            text = "$${"%.2f".format(totalPrice)}",
            style = MaterialTheme.typography.bodyLarge,
            color = black
        )
    }
}

@Composable
fun CreateOrderButton(hasItemsInCart: Boolean, onCheckoutClick: () -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        enabled = hasItemsInCart,
        onClick = onCheckoutClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = "Create order")
    }
}


