package com.spotride.mobile.model.user.model

import kotlinx.serialization.Serializable

/**
 * User response DTO model.
 */
@Serializable
data class UserResponseDto(
    val id: Long,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val birthDate: String,
    val city: String
)
