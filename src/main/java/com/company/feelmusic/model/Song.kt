package com.company.feelmusic.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "songs")
data class Song(

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String?,

    @Column(name = "created_date")
    @CreationTimestamp
    val createdDate: LocalDateTime?,

    @Column(name = "updated_date")
    @UpdateTimestamp
    val updatedDate: LocalDateTime?,

    @Column(name = "name")
    val name: String?,

    @Column(name = "singer")
    val singer: String?,


    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "category")
    val category: Category?,

    @Column(name = "voice_url")
    val voiceUrl: String?,

    @Column(name = "lyrics")
    val lyrics: String?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val image: Image?,

    @ManyToMany
    @JsonIgnore
    val feelContainers: List<FeelContainer>?

) {
    data class Builder(
        var id: String? = null,
        var createdDate: LocalDateTime? = null,
        var updatedDate: LocalDateTime? = null,
        var name: String? = null,
        var singer: String? = null,
        var category: Category? = null,
        var voiceUrl: String? = null,
        var lyrics: String? = null,
        var image: Image? = null,
        var feelContainers: List<FeelContainer>? = null
    ) {
        fun name(name: String) = apply { this.name = name }
        fun singer(singer: String) = apply { this.singer = singer }
        fun category(category: Category) = apply { this.category = category }
        fun voiceUrl(voiceUrl: String) = apply { this.voiceUrl = voiceUrl }
        fun lyrics(lyrics: String) = apply { this.lyrics = lyrics }
        fun image(image: Image) = apply { this.image = image }
        fun feelContainers(feelContainers: List<FeelContainer>?) = apply { this.feelContainers = feelContainers }

        fun build() =
            Song(id, createdDate, updatedDate, name, singer, category, voiceUrl, lyrics, image, feelContainers)
    }
}