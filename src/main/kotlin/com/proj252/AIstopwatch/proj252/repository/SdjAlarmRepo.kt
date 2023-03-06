package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SdjEventRepo: JpaRepository<Event,Long> {
    fun findByGroupId(groupId: Long): Optional<Event>

    //쿼리문을 직접 짜야하지 않을까?
    fun findByHost(userId: Long): Optional<Event>{

    }
    fun findByPartis(userId: Long): Optional<Event>{

    }
}