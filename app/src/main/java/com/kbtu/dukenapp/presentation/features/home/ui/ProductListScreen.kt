package com.kbtu.dukenapp.presentation.features.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kbtu.dukenapp.presentation.features.home.mvi.Intent
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.ui.theme.AccentTealColor
import com.kbtu.dukenapp.ui.theme.SoftGrayBorder
import com.kbtu.dukenapp.ui.theme.SoftWhiteCard
import com.kbtu.dukenapp.ui.theme.black

@Composable
fun ProductListScreen(products: List<ProductUiModel>, performIntent: (Intent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(products) { product ->
            ProductItem(product = product) {
                performIntent.invoke(Intent.OnProductClick(productId = product.productId))
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductItem(product: ProductUiModel, onProductClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onProductClick() },
        colors = CardColors(
            containerColor = SoftWhiteCard,
            contentColor = SoftWhiteCard,
            disabledContentColor = SoftWhiteCard,
            disabledContainerColor = SoftWhiteCard
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val pagerState = rememberPagerState()

            HorizontalPager(
                count = product.images.size, // Number of images
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { page ->
                Image(
                    painter = rememberAsyncImagePainter(product.images[page]),
                    contentDescription = "Product image ${page + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            DotsIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = product.name.trim(),
                    color = black
                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(text = product.description, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${product.price}",
                    color = Color.Black
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DotsIndicator(
    pagerState: com.google.accompanist.pager.PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = AccentTealColor, // Use Accent Teal Color for active dots
    inactiveColor: Color = SoftGrayBorder
) {
    val dotSize = 8.dp
    val dotSpacing = 4.dp

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (page in 0 until pagerState.pageCount) {
            val isSelected = pagerState.currentPage == page
            Box(
                modifier = Modifier
                    .padding(end = dotSpacing)
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(if (isSelected) activeColor else inactiveColor)
            )
        }
    }
}