package com.company.feelmusic.dto.response

data class TokenResponse(
    val accessToken: String?,
    val userDto: UserResponseDto?
)