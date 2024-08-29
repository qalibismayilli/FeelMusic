package com.company.feelmusic.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "images")
data class Image(

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String?,

    @Column(name = "created_date")
    @CreationTimestamp
    val createdDate: LocalDateTime?,

    @Column(name = "updated_date")
    @UpdateTimestamp()
    val updatedDate: LocalDateTime?,

    @Column(name= "name")
    val name: String?,

    @Column(name = "image_url")
    val imageUrl: String?,

    @OneToMany(mappedBy = "image", cascade = [CascadeType.ALL])
    val songs: List<Song>?

) {
    data class Builder(
        var id: String? = null,
        var createdDate: LocalDateTime? = null,
        var updatedDate: LocalDateTime? = null,
        var name: String? = null,
        var imageUrl: String? = null,
        var songs: List<Song>? = null
    ) {
        fun name(name: String) = apply { this.name = name }
        fun imageUrl(imageUrl: String) = apply { this.imageUrl = imageUrl }
        fun songs(songs: List<Song>) = apply { this.songs = songs }

        fun build() = Image(id, createdDate, updatedDate,name, imageUrl, songs)
    }
}
