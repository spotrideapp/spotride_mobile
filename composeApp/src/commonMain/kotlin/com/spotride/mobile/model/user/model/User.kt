package com.spotride.mobile.model.user.model

/**
 * User data model.
 */
data class User(
    val id: Long,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val birthDate: String,
    val city: String
)