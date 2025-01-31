package com.spotride.mobile.ui.screen.registration

import com.spotride.mobile.model.user.api.UserApiService

/**
 * Controller for RegistrationScreen.
 */
class RegistrationScreenController(private val userApiService: UserApiService) {

    suspend fun doRegisterUser(email: String, password: String) {
        try {
            userApiService.registerUser(email, password).getOrThrow()
        } catch (exception: Exception) {
            // TODO: log this exception
        }
    }
}