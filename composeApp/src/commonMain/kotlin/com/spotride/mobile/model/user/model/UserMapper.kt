package com.spotride.mobile.model.user.model

/**
 * Mapper for User.
 */
class UserMapper {

    fun toEntity(userResponseDto: UserResponseDto): User {
        return User(
            id = userResponseDto.id,
            username = userResponseDto.username,
            email = userResponseDto.email,
            firstName = userResponseDto.firstName,
            lastName = userResponseDto.lastName,
            phoneNumber = userResponseDto.phoneNumber,
            birthDate = userResponseDto.birthDate,
            city = userResponseDto.city
        )
    }
}