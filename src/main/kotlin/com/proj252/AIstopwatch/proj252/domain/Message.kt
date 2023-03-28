package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "message")
data class Message(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "type")
    val type: String,
    @Column(name = "group_id")
    val groupId: Long,
    @Column(name = "time")
    val time: LocalDateTime,

    @ManyToOne @JoinColumn(name = "user_id")
    val user: User,


    )