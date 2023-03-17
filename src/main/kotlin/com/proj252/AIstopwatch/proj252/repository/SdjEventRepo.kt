package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SdjEventRepo: JpaRepository<Event,Long> {
    fun findEventByGroupId(groupId: Long): List<Event>
    //=> 아무것도 찾지 못했을 때 [] 빈리스트를 리턴하게 된다!

    //TODO event를 정해진 로직인 하나만 받아올 수 있게 적용
    fun getEventByGroupId(groupId: Long): Event?

}