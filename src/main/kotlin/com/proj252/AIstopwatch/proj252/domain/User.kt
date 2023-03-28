package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val uid: Long? = null,

    @Column(name = "name")
    var name: String,
    @Column(name = "id")
    val id: String,
    @Column(name = "password")
    val password: String,

    @OneToMany(mappedBy = "related_group", cascade = [CascadeType.ALL])
    var relatedGroups: MutableList<RelatedGroup>,
    @OneToMany(mappedBy = "message", cascade = [CascadeType.ALL])
    var messages: MutableList<Message>,
    @OneToMany(mappedBy = "attendance", cascade = [CascadeType.ALL])
    var attendances: MutableList<Attendance>,
) {
    constructor(name: String, id: String, password: String) : this(
        null,
        name,
        id,
        password,
        mutableListOf(),
        mutableListOf(),
        mutableListOf()
    )
}