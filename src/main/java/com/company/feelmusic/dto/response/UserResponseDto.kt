package com.company.feelmusic.dto.response

import com.company.feelmusic.model.Role

data class UserResponseDto(
    val userName: String?,
    val email: String?,
    val role: Role?
)
