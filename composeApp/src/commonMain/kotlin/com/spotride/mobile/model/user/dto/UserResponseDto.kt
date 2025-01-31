package com.spotride.mobile.model.user.dto

import kotlinx.serialization.Serializable

/**
 * User response DTO model.
 */
@Serializable
data class UserResponseDto(
    val id: Long,
    val email: String,
    val password: String,
    val username: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val birthDate: String? = null,
    val city: String? = null
)
