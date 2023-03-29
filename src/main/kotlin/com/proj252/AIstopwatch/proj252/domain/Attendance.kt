package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.*

@Entity
@Table(name = "attendance")
data class Attendance(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "attend")
    var attend: Int = 1,
    @Column(name = "comment")
    var comment: String?,

    @ManyToOne @JoinColumn(name = "event_id")
    val event: Event,
    @ManyToOne @JoinColumn(name = "user_id")
    val user: Event,

)