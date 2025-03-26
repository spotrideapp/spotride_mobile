package com.spotride.mobile.authentication

import kotlinx.serialization.Serializable

/**
 * User authentication request DTO model.
 */
@Serializable
data class AuthenticationRequestDto(
    val email: String,
    val password: String
)
