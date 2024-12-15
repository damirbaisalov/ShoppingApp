package com.kbtu.dukenapp.utils.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.random.Random

@Composable
fun <T> SharedFlow<T>.CollectAsSideEffect(handler: suspend (effect: T) -> Unit) {

    var sideEffect by remember { mutableStateOf<T?>(null) }
    var sideEffectKey by remember { mutableStateOf("") }

    LaunchedEffect(sideEffectKey) {
        sideEffect?.let { handler.invoke(it) }
    }

    LaunchedEffect("SideEffect") {
        onEach {
            sideEffect = it
            sideEffectKey = "$it${Random.nextLong()}"
        }.launchIn(this)
    }
}