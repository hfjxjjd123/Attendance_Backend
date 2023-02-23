package com.proj252.AIstopwatch.proj252.u_domain

import jakarta.persistence.*

data class Mate(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: CustomUser,

    @ManyToOne
    @JoinColumn(name = "group_id")
    val group: Group
)