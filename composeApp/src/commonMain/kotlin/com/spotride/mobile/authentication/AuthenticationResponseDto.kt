package com.spotride.mobile.authentication

import kotlinx.serialization.Serializable

/**
 * User authentication response DTO model.
 */
@Serializable
data class AuthenticationResponseDto(
    val token: String
)
