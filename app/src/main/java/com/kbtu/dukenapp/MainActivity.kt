package com.kbtu.dukenapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alphicc.brick.AndroidComponentsContainer
import com.alphicc.brick.TreeRouter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kbtu.dukenapp.ui.theme.white
import androidx.compose.animation.ExperimentalAnimationApi
import com.kbtu.dukenapp.presentation.features.bottom_menu.bottomMenuScreen
import org.koin.android.ext.android.inject

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val router: TreeRouter by inject<TreeRouter>()
        if (router.currentComponentKey() == null) {
            router.cleanRouter()
            router.addComponent(bottomMenuScreen)
        }
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = white)
            systemUiController.setNavigationBarColor(color = white)
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                AndroidComponentsContainer(
                    containerConnector = router,
                    onRouterEmpty = { finish() }
                )
            }
        }
    }
}