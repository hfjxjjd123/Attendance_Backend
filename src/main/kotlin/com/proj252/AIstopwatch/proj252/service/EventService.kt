package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.dto.event.EventDto
import com.proj252.AIstopwatch.proj252.repository.SdjEventRepo
import com.proj252.AIstopwatch.proj252.repository.SdjGroupRepo
import com.proj252.AIstopwatch.proj252.repository.SdjRelatedGroupRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Date

@Service
@Transactional
//!! Service니까 날짜 변화시 초기화 등이 여기서 반영되어야 한다는 것. 각자마다 조회 & 날짜 변경 확인 & 이후 진행하는 로직을 만들 것.
class EventService {
    private var eventRepo: SdjEventRepo
    private var groupRepo: SdjGroupRepo
    private var relatedGroupRepo: SdjRelatedGroupRepo

    @Autowired
    //여기서 repo 갈아끼울 수 있음
    constructor(eventRepo: SdjEventRepo, groupRepo: SdjGroupRepo, relatedGroupRepo: SdjRelatedGroupRepo) {
        this.eventRepo = eventRepo
        this.groupRepo = groupRepo
        this.relatedGroupRepo = relatedGroupRepo
    }

    public fun getRecentEventByGroup(groupId: Long): Event? {
        var event: Event? = null

        try {
            event = eventRepo.findFirstByGroupIdOrderByNextScheduleDesc(groupId)

        } catch (e: Exception) {
            print("stopwatch get time err, retry?")
        }

        return event

    }

    //역할에 맞는 Group을 불러오는 로직
    public fun getGroupsByUser(uid: Long, role: Int): List<Long> {

        var groupsId: List<Long>

        //groupId를 얻어오고,
        //얻어 온 group_id로 조회해본 group이 존재하면 List에 눌러담기
        try {
            groupsId = relatedGroupRepo.findGroupIdByUserUidAndRole(uid, role)
        } catch (e: Exception) {
            groupsId = listOf<Long>()
            //TODO error handling
        }
        return groupsId
    }

    public fun getEventsByGroups(groups: List<Long>): List<Event> {
        var events = mutableListOf<Event>()

        try {
            for (groupId in groups) {
                eventRepo.findFirstByGroupIdOrderByNextScheduleDesc(groupId)?.let {
                    events.add(it)
                }
            }

        } catch (e: Exception) {
            //TODO error handling
        }

        return events
    }

    public fun createEvent(eventDto: EventDto) {

        val group: Group? = groupRepo.getGroupById(eventDto.groupId)
        group?.let {
            val event: Event =
                Event(
                    nextSchedule = eventDto.nextSchedule,
                    name = eventDto.name,
                    rule = eventDto.rule,
                    code = createCode(),
                    group = group
                )
            eventRepo.save(event)
        } ?: run {
            print("일어나면 안되는 일이다.")
        }
    }

    private fun createCode(): String{
        val charset = "ABCDEF0123456789" // Define the character set to choose from
        val length = 4 // String 길이

        val randomString = (1..length)
            .map { charset.random() }
            .joinToString("")

        return randomString
    }


    public fun changeEvent(eventDto: EventDto){
        var event: Event? = eventRepo.getEventById(eventDto.id)
        event?.apply {
            name = eventDto.name
            nextSchedule = eventDto.nextSchedule
            rule = eventDto.rule
        }

        event?. let {
            eventRepo.save(event)

        }?:{
            print("이 에러는 나오면 안됩니다.")
        }

    }


}