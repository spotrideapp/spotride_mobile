package com.spotride.mobile.ui.screen.startscreen.startscreen

import com.spotride.mobile.model.user.service.UserApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Controller for StartScreen.
 */
class ScreenController(private val userApiService: UserApiService) {

    private val _state = MutableStateFlow<State>(State.NoData)

    val state: StateFlow<State> = _state

    suspend fun getUserById(id: Int) {
        _state.value = State.Loading
        try {
            val user = userApiService.getUserById(id).getOrThrow()
            _state.value = State.Success(user)
        } catch (exception: Exception) {
            _state.value = State.Error(exception.message ?: "Unknown error")
        }
    }
}