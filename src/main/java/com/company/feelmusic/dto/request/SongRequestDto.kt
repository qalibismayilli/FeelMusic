package com.company.feelmusic.dto.request


data class SongRequestDto(
    val name: String?,
    val singer: String?,
    val categoryName: String?,
    val voiceUrl: String?,
    val lyrics: String?
)
