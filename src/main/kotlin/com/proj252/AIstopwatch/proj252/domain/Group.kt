package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.*

data class Group(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name")
    val name: String,

    @OneToMany(mappedBy = "related_user", cascade = [CascadeType.ALL])
    var relatedUsers: MutableList<RelatedUser>,
)