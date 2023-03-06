package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.*

data class Partis(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: CustomUser,
    @ManyToOne
    @JoinColumn(name = "group_id")
    val group: Group,

    @Column(name = "event_counter")
    val eventCounter: Int,
    @Column(name = "attendances")
    val attendances: Int,
    @Column(name = "natttend")
    val nattend: Int,

)