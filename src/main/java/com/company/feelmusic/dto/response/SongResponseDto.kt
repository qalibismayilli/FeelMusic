package com.company.feelmusic.dto.response

import com.company.feelmusic.model.Image

data class SongResponseDto(
    val name: String?,
    val singer: String?,
    val categoryName: String?,
    val lyrics: String?,
    val image: Image?
)
