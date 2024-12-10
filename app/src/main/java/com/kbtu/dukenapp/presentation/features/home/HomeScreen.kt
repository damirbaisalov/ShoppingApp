package com.kbtu.dukenapp.presentation.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.kbtu.dukenapp.domain.model.characters.ProductItem
import com.kbtu.dukenapp.presentation.state.ComponentRectangleLineLong
import com.kbtu.dukenapp.presentation.state.ManagementResourceUiState
import com.kbtu.dukenapp.ui.theme.Paddings
import com.kbtu.dukenapp.utils.extension.noRippleClickable
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Paddings.medium)
    ) {
        ManagementResourceUiState(
            modifier = Modifier.padding(bottom = Paddings.medium),
            resourceUiState = state.productList,
            successView = {
                Column {
                    it.forEach { it ->
                        ProductItem(
                            productItem = it,
                            onClick = {

                            }
                        )
                    }
                }
            },
            loadingView = {
                Column {
                    for (i in 1..5) {
                        ItemOnLoading()
                    }
                }
            },
            onCheckAgain = {},
            onTryAgain = {},
        )
    }
}

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    productItem: ProductItem,
    onClick: (ProductItem) -> Unit
) {
    Box(
        modifier = modifier
            .padding(top = Paddings.xxSmall, bottom = Paddings.small6dp)
            .shadow(Paddings.extraSmall, shape = RoundedCornerShape(Paddings.small))
            .background(Color.White, RoundedCornerShape(Paddings.small))
            .fillMaxWidth()
            .wrapContentHeight()
            .noRippleClickable { onClick(productItem) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(Paddings.medium),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = productItem.name,
                maxLines = 1,
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = productItem.name,
                    maxLines = 1,
                )
                Text(
                    text = productItem.description,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun ItemOnLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(top = Paddings.xxSmall, bottom = Paddings.small6dp)
            .shadow(Paddings.extraSmall, shape = RoundedCornerShape(Paddings.small))
            .background(Color.White, RoundedCornerShape(Paddings.small))
            .fillMaxWidth()
            .wrapContentHeight()
            .wrapContentHeight()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(Paddings.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ComponentRectangleLineLong()
        }
    }
}
