package com.spotride.mobile

import androidx.compose.ui.window.ComposeUIViewController
import com.spotride.mobile.di.initKoin
import com.spotride.mobile.ui.screen.loginscreen.LoginScreen

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    LoginScreen()
}