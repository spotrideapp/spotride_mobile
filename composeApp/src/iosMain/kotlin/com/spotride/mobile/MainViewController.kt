package com.spotride.mobile

import androidx.compose.ui.window.ComposeUIViewController
import com.spotride.mobile.model.service.UserApiService
import com.spotride.mobile.ui.StartScreen

fun MainViewController() = ComposeUIViewController { StartScreen(UserApiService()) }