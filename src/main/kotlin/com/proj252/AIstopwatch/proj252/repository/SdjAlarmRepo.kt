package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SdjEventRepo: JpaRepository<Event,Long> {
    fun findByGroupId(groupId: Long): Optional<Event>

    //쿼리문로직 1
    //userId => user => host => group => event

    fun findByHost(userId: Long): Optional<Event>{
        "SELECT e FROM Event e WHERE e.groupId = :email AND u.password = :password")

    }
    fun findByPartis(userId: Long): Optional<Event>{

    }
}