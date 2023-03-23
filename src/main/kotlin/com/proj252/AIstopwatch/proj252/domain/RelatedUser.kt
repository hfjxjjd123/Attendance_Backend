package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.*

@Entity
@Table(name = "related_user")
data class RelatedUser(
    @Id
    val userUid: Long,

    @Column(name = "role")
    val role: Int,
    @Column(name = "username")
    var username: String,
    @Column(name = "recent_attends")
    val recentAttends: Int?,
    @Column(name = "next_attend")
    val nextAttend: Int?,

    @ManyToOne @JoinColumn(name = "group_id")
    val group: Group

)