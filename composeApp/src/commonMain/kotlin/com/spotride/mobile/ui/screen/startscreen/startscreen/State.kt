package com.spotride.mobile.ui.screen.startscreen.startscreen

import com.spotride.mobile.model.user.model.User

/**
 * State of User.
 */
sealed class State {

    /**
     * NoData - there is no data.
     */
    data object NoData : State()
    /**
     * Loading — loading state.
     */
    data object Loading : State()

    /**
     * Success — user data state.
     */
    data class Success(val user: User) : State()

    /**
     * Error — state with error message.
     */
    data class Error(val message: String) : State()
}
