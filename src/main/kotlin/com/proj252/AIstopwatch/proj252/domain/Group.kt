package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.*

@Entity
@Table(name = "group")
data class Group(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name")
    var name: String,
    @Column(name = "notification")
    var notification: String?,

    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL])
    var events: MutableList<Event> = mutableListOf(),
    @OneToMany(mappedBy = "related_user", cascade = [CascadeType.ALL])
    var relatedUsers: MutableList<RelatedUser> = mutableListOf(),
)