package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.*
import java.sql.Date


@Entity
@Table(name = "event")
data class Event(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "next_schedule")
    var nextSchedule: Date,
    @Column(name = "name")
    var name: String,
    @Column(name = "rule")
    var rule: Int,
    @Column(name = "code")
    val code: String,

    @ManyToOne @JoinColumn(name = "group_id")
    val group: Group,
    @OneToMany(mappedBy = "attendance", cascade = [CascadeType.ALL])
    var attendances: MutableList<Attendance>,
)
