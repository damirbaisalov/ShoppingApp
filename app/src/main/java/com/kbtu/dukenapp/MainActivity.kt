package com.kbtu.dukenapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alphicc.brick.AndroidComponentsContainer
import com.alphicc.brick.TreeRouter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kbtu.dukenapp.presentation.features.home.implementation.HomeRouterImpl
import com.kbtu.dukenapp.presentation.features.home.implementation.homeComponent
import com.kbtu.dukenapp.ui.theme.white
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val router: TreeRouter by inject<TreeRouter>()

        if (router.currentComponentKey() == null) {
            router.cleanRouter()
            val splashComponentArgument = HomeRouterImpl.Args()
            router.addComponent(homeComponent, splashComponentArgument)
        }
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = white)
            systemUiController.setNavigationBarColor(color = white)
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.systemBars.asPaddingValues())
            ) {
                AndroidComponentsContainer(router) {
                    finish()
                }
            }
        }
    }
}