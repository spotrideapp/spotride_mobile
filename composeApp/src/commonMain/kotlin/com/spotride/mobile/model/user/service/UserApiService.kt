package com.spotride.mobile.model.user.service

import com.spotride.mobile.model.user.model.User
import com.spotride.mobile.model.user.model.UserMapper
import com.spotride.mobile.model.user.model.UserResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Service to provide communication with the backend.
 */
class UserApiService(private val userMapper: UserMapper) {

    private val url = "http://10.0.2.2:8080/users"

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
            val userResponseDto = client.get("$url/$id").body<UserResponseDto>()
            val user = userMapper.toEntity(userResponseDto)
            Result.success(user)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}