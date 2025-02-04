package com.spotride.mobile.model.user.model

/**
 * User data model.
 */
data class User(
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