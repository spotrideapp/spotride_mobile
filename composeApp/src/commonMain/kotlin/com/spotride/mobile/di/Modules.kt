package com.spotride.mobile.di

import com.spotride.mobile.model.user.model.UserMapper
import com.spotride.mobile.model.user.service.UserApiService
import com.spotride.mobile.ui.screen.startscreen.ScreenController
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModule = module {
    singleOf(::UserApiService)
    singleOf(::UserMapper)
    singleOf(::ScreenController)
}