package com.spotride.mobile.authentication

/**
 * Storage for authentication token.
 */
class TokenStorage {
    private var token: String? = null

    fun saveToken(token: String) :String {
        this.token = token
        return token
    }

    fun getToken() :String? {
        return token
    }

    fun clearToken() {
        token = null
    }
}
