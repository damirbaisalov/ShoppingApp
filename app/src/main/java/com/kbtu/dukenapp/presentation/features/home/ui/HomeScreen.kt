package com.kbtu.dukenapp.presentation.features.home.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kbtu.dukenapp.presentation.features.home.mvi.Intent
import com.kbtu.dukenapp.presentation.features.home.mvi.ScreenState
import com.kbtu.dukenapp.presentation.features.home.mvi.State
import com.kbtu.dukenapp.ui.theme.LightBlueBackground
import com.kbtu.dukenapp.ui.theme.black
import com.kbtu.dukenapp.ui.theme.green3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(state: State, performIntent: (Intent) -> Unit) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
        onRefresh = { performIntent(Intent.OnRefreshClick) }
    ) {
        Scaffold(
            contentColor = LightBlueBackground,
            containerColor = LightBlueBackground,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "E-Shop",
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 24.sp,
                                color = black,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    },
                    actions = {
                        IconButton(
                            modifier = Modifier.padding(end = 16.dp),
                            onClick = { performIntent(Intent.OnProfileClick) }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Account",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = LightBlueBackground,
                        titleContentColor = black
                    )
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (state.screenState) {
                    is ScreenState.ErrorLoad -> ErrorScreen { performIntent(Intent.OnRefreshClick) }
                    is ScreenState.Loading -> Unit
                    is ScreenState.SuccessLoad -> {
                        CategoryList(categories = state.categories, performIntent = performIntent)
                        ProductListScreen(state.screenState.data, performIntent = performIntent)
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorScreen(onRetryClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Something went wrong. Please try again.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRetryClick
            ) {
                Text(text = "Retry")
            }
        }
    }
}
