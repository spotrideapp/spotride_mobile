package com.spotride.mobile.ui.screen.registration

import com.spotride.mobile.authentication.AuthenticationApiService

/**
 * Controller for RegistrationScreen.
 */
class RegistrationScreenController(private val authenticationApiService: AuthenticationApiService) {

    suspend fun doRegisterUser(email: String, password: String) {
        try {
            authenticationApiService.authenticateUser(email, password).getOrThrow()
        } catch (exception: Exception) {
            // TODO: log this exception
        }
    }
}
