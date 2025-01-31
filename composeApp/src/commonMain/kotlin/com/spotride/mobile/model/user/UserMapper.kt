package com.spotride.mobile.model.user

import com.spotride.mobile.model.user.dto.UserResponseDto
import com.spotride.mobile.model.user.model.User

/**
 * Mapper for User.
 */
class UserMapper {

    fun toEntity(userResponseDto: UserResponseDto): User {
        return User(
            id = userResponseDto.id,
            username = userResponseDto.username,
            password = userResponseDto.password,
            email = userResponseDto.email,
            firstName = userResponseDto.firstName,
            lastName = userResponseDto.lastName,
            phoneNumber = userResponseDto.phoneNumber,
            birthDate = userResponseDto.birthDate,
            city = userResponseDto.city
        )
    }
}