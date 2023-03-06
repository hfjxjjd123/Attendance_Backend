package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.repository.SdjEventRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
//!! Service니까 날짜 변화시 초기화 등이 여기서 반영되어야 한다는 것. 각자마다 조회 & 날짜 변경 확인 & 이후 진행하는 로직을 만들 것.
class EventService {
    private lateinit var eventRepo: SdjEventRepo

    @Autowired
    //여기서 repo 갈아끼울 수 있음
    constructor(eventRepo: SdjEventRepo){
        this.eventRepo = eventRepo
    }

    public fun getEventByGroup(groupId: Long): Event?{

        try {
            val event: Event? =
                eventRepo.findByGroupId(groupId).orElse(null)

            return event

        }catch (e: Exception){
            print("stopwatch get time err, retry?")
            return null
        }

    }

    public fun getEventByHost(userId: Long): Event?{

        try {
            val event: Event? =
                eventRepo.findByHost(userId).orElse(null)

            return event

        }catch (e: Exception){
            print("stopwatch get time err, retry?")
            return null
        }

    }
    public fun getEventByPartis(userId: Long): Event?{

        try {
            val event: Event? =
                eventRepo.findByPartis(userId).orElse(null)

            return event

        }catch (e: Exception){
            print("stopwatch get time err, retry?")
            return null
        }

    }

    //

    //


}