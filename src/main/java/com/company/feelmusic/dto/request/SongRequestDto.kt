package com.company.feelmusic.dto.request

import com.company.feelmusic.model.Image

data class SongRequestDto(
    val name: String?,
    val singer: String?,
    val categoryId: String?,
    val voiceUrl: String?,
    val lyrics: String?,
    val image: Image?

    )
