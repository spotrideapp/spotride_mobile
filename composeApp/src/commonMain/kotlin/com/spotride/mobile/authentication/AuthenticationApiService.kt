package com.spotride.mobile.authentication

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Api to communicate with backend for user authentication.
 */
class AuthenticationApiService(private val tokenStorage: TokenStorage) {
    private val registrationUrl = "http://10.0.2.2:8080/auth/register"

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

    suspend fun authenticateUser(email: String, password: String): Result<String> {
        return try {
            val authenticationRequestDto = AuthenticationRequestDto(email, password)
            val body = client.post(registrationUrl) {
                contentType(ContentType.Application.Json)
                setBody(authenticationRequestDto)
            }.body<AuthenticationResponseDto>()
            Result.success(tokenStorage.saveToken(body.token))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
