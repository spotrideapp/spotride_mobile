package com.spotride.mobile.model.user.api

import com.spotride.mobile.model.user.UserMapper
import com.spotride.mobile.model.user.dto.UserResponseDto
import com.spotride.mobile.model.user.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Api to communicate with backend for user handling.
 */
class UserApiService(private val userMapper: UserMapper) {
    private val usersUrl = "http://10.0.2.2:8080/users"

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging) {
            level = LogLevel.BODY
        }
    }

    suspend fun getUserById(id: Int): Result<User> {
        return try {
            val userResponseDto = client.get("$usersUrl/$id").body<UserResponseDto>()
            val user = userMapper.toEntity(userResponseDto)
            Result.success(user)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
