package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "attendance")
data class Attendance(
    @Id
    val userId: Long,

    @Column(name = "attend")
    val attend: Int,

    @ManyToOne @JoinColumn(name = "event_id")
    val event: Event,

)