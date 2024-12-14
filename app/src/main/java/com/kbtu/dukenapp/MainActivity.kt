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
import com.kbtu.dukenapp.ui.theme.white
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.alphicc.brick.Component
import com.kbtu.dukenapp.presentation.features.bottom_menu.bottomMenuScreen
import com.kbtu.dukenapp.presentation.features.home.implementation.HomeRouterImpl
import org.koin.android.ext.android.inject

val component1 by lazy {
    Component<Unit>(
        key = "CompositeScreenInternal 1",
        content = { _, _ -> Text("CompositeScreenInternal 1") }
    )
}

val component2 by lazy {
    Component<Unit>(
        key = "CompositeScreenInternal 2",
        content = { _, _ -> Text("CompositeScreenInternal 2") }
    )
}

val component3 by lazy {
    Component<Unit>(
        key = "CompositeScreenInternal 3",
        content = { _, _ ->
            Button({}) {
                Text("CompositeScreenInternal 3")
            }
        }
    )
}

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val router: TreeRouter by inject<TreeRouter>()

        if (router.currentComponentKey() == null) {
            router.cleanRouter()
            val splashComponentArgument = HomeRouterImpl.Args()
            router.addComponent(bottomMenuScreen)
        }
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = white)
            systemUiController.setNavigationBarColor(color = white)
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
            ) {
                AndroidComponentsContainer(
                    containerConnector = router,
                    onRouterEmpty = { finish() }
                )
            }
        }

//        if (savedInstanceState == null) {
//            largeSampleRouter.addComponent(bottomMenuScreen)
//        }
    }
}