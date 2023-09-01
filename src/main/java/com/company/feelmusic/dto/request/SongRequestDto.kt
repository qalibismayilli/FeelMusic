package com.company.feelmusic.dto.request

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.File

data class SongRequestDto(
    val name: String?,
    val singer: String?,
    val categoryName: String?,
    val voiceUrl: String?,
    val lyrics: String?,
    val imageFile: File?

) {
    @JsonCreator
    constructor(name: String?,
                singer: String?,
                categoryName: String?,
                voiceUrl: String?,
                lyrics: String?) : this(
                    name = name,
                    singer = singer,
                    categoryName = categoryName,
                    voiceUrl = voiceUrl,
                    lyrics = lyrics,
                    imageFile = null
                )
}
