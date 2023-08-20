package com.company.feelmusic.model

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
    @ManyToMany
    @JoinTable(name = "songs_singers")
    val singers: List<Singer>?,

    @Enumerated(EnumType.STRING)
    val feel: Feel?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "category")
    val category: Category?,

    @Column(name = "voice_url")
    val voiceUrl: String?,

    @Column(name = "lyrics")
    val lyrics: String?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val image: Image?

) {
    data class Builder(
        var id: String? = null,
        var createdDate: LocalDateTime? = null,
        var updatedDate: LocalDateTime? = null,
        var name: String? = null,
        var singers: List<Singer>? = null,
        var feel: Feel? = null,
        var category: Category? = null,
        var voiceUrl: String? = null,
        var lyrics: String? = null,
        var image: Image? = null
    ) {
        fun name(name: String) = apply { this.name = name }
        fun singers(singers: List<Singer>) = apply { this.singers }
        fun feel(feel: Feel) = apply { this.feel = feel }
        fun category(category: Category) = apply { this.category = category }
        fun lyrics(lyrics: String) = apply { this.lyrics = lyrics }
        fun image(image: Image) = apply { this.image = image }

        fun build() = Song(id, createdDate, updatedDate, name, singers, feel, category, voiceUrl, lyrics, image)
    }
}