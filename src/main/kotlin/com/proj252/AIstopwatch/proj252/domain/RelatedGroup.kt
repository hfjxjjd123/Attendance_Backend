package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "related_group")
data class RelatedGroup(
    @Id
    val groupId: Long,

    @Column(name = "role")
    val role: Int,

    @ManyToOne @JoinColumn(name = "user_uid")
    val user:User

)