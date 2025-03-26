package com.spotride.mobile.di

import com.spotride.mobile.model.user.UserMapper
import com.spotride.mobile.model.user.api.UserApiService
import com.spotride.mobile.ui.screen.loginscreen.LoginScreenController
import com.spotride.mobile.ui.screen.registration.RegistrationScreenController
import com.spotride.mobile.authentication.AuthenticationApiService
import com.spotride.mobile.authentication.TokenStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModule = module {
    singleOf(::UserApiService)
    singleOf(::AuthenticationApiService)
    singleOf(::UserMapper)
    singleOf(::RegistrationScreenController)
    singleOf(::LoginScreenController)
    singleOf(::TokenStorage)
}
