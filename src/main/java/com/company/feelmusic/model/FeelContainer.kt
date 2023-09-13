package com.company.feelmusic.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
data class FeelContainer(
    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    val id: String?,

    @Column(name = "created_Date")
    @CreationTimestamp
    val createdDate: LocalDateTime?,

    @Column(name = "Updated_date")
    @UpdateTimestamp
    val updatedDate: LocalDateTime?,

    @Column(name = "feel")
    @Enumerated(EnumType.STRING)
    val feel: Feel?,

    @ManyToMany(mappedBy = "feelContainers", fetch = FetchType.LAZY)
    val songs: List<Song>?,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User?

)
