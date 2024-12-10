package com.kbtu.dukenapp.presentation.state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kbtu.dukenapp.ui.theme.grayLight
import com.kbtu.dukenapp.ui.theme.greyColorAlpha
import com.kbtu.dukenapp.utils.extension.shimmerLoadingAnimation

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = greyColorAlpha)
            .height(250.dp)
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentSquare(
    isLoadingCompleted: Boolean,
    isLightModeActive: Boolean,
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(24.dp))
            .background(color = grayLight)
            .size(100.dp)
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentRectangle(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = grayLight)
            .height(200.dp)
            .fillMaxWidth()
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentCircle(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .background(color = grayLight)
            .then(modifier)
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentRectangleLineLong(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = grayLight)
            .height(20.dp)
            .fillMaxWidth()
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentRectangleVerticalLineLong() {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = grayLight)
            .width(20.dp)
            .fillMaxHeight()
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentRectangleLineShort(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = grayLight)
            .height(20.dp)
            .fillMaxWidth(0.5f)
            .shimmerLoadingAnimation()
    )
}