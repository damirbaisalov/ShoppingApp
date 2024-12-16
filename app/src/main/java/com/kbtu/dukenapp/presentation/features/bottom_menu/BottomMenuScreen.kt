package com.kbtu.dukenapp.presentation.features.bottom_menu

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alphicc.brick.AndroidComponentsContainer
import com.alphicc.brick.TreeRouter
import com.kbtu.dukenapp.ui.theme.LightBlueBackground

@ExperimentalAnimationApi
@Composable
fun BottomMenuScreen(
    defaultIndex: Int,
    containerRouter: TreeRouter,
    onFirstMenuClicked: () -> Unit,
    onSecondMenuClicked: () -> Unit,
    onThirdMenuClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(bottom = 78.dp)
                .fillMaxSize()
        ) {
            AndroidComponentsContainer(containerRouter) {
                containerRouter.parentRouter?.cleanRouter()
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = LightBlueBackground),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomNavigationBar(
                defaultIndex = defaultIndex,
                onHomeScreenClicked = onFirstMenuClicked,
                onNavigateToCategories = onSecondMenuClicked,
                onNavigateToProfile = onThirdMenuClicked
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    defaultIndex: Int,
    onHomeScreenClicked: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToCategories: () -> Unit
) {
    NavigationBar(
        modifier = Modifier.wrapContentHeight(),
        containerColor = LightBlueBackground
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") },
            selected = defaultIndex == 0,
            onClick = { onHomeScreenClicked() }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Cart"
                )
            },
            label = { Text("Cart") },
            selected = defaultIndex == 1,
            onClick = { onNavigateToCategories() }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile"
                )
            },
            label = { Text("Profile") },
            selected = defaultIndex == 2,
            onClick = { onNavigateToProfile() }
        )
    }
}