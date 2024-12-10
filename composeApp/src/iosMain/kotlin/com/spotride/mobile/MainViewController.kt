package com.spotride.mobile

import androidx.compose.ui.window.ComposeUIViewController
import com.spotride.mobile.model.user.service.UserApiService
import com.spotride.mobile.ui.screen.startscreen.StartScreen

fun MainViewController() = ComposeUIViewController { StartScreen(UserApiService()) }