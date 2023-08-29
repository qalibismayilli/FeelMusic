package com.company.feelmusic.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "categories")
data class Category(

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

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL])
    val songs: List<Song>?
) {
    data class Builder(
        var id: String? = null,
        var createdDate: LocalDateTime? = null,
        var updatedDate: LocalDateTime? = null,
        var name: String? = null,
        var songs: List<Song>? = null,
    ) {
        fun name(name: String?) = apply { this.name = name }
        fun songs(songs: List<Song>) = apply { this.songs = songs }

        fun build() = Category(id, createdDate, updatedDate, name, songs)
    }


}
