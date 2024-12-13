package com.kbtu.dukenapp.presentation.features.product_screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.ui.theme.black

@Composable
fun ProductDetailContent(product: ProductUiModel) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        LazyRow(
            modifier = Modifier.padding(top = 24.dp)
        ) {
            items(product.images) {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .padding(bottom = 16.dp)
                )
            }
        }

        // Product Name
        Text(
            text = product.name,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = black,
                fontSize = 28.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Product Description
        Text(
            text = product.description,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                color = black,
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Product Price
        Text(
            text = "$${product.price}",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = black,
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { /* Handle the "Buy Now" or add to cart */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Buy Now")
        }
    }
}