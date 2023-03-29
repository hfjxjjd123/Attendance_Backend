package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.domain.Attendance
import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.dto.event.EventDto
import com.proj252.AIstopwatch.proj252.repository.SdjAttendanceRepo
import com.proj252.AIstopwatch.proj252.repository.SdjEventRepo
import com.proj252.AIstopwatch.proj252.repository.SdjGroupRepo
import com.proj252.AIstopwatch.proj252.repository.SdjRelatedGroupRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
//!! Service니까 날짜 변화시 초기화 등이 여기서 반영되어야 한다는 것. 각자마다 조회 & 날짜 변경 확인 & 이후 진행하는 로직을 만들 것.
class EventService {
    private var eventRepo: SdjEventRepo
    private var groupRepo: SdjGroupRepo
    private var relatedGroupRepo: SdjRelatedGroupRepo
    private var attendanceRepo: SdjAttendanceRepo


    @Autowired
    //여기서 repo 갈아끼울 수 있음
    constructor(
        eventRepo: SdjEventRepo,
        groupRepo: SdjGroupRepo,
        relatedGroupRepo: SdjRelatedGroupRepo,
        attendanceRepo: SdjAttendanceRepo
    ) {
        this.eventRepo = eventRepo
        this.groupRepo = groupRepo
        this.relatedGroupRepo = relatedGroupRepo
        this.attendanceRepo = attendanceRepo
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

    public fun createEvent(eventDto: EventDto, gid: Long) {

        val group: Group? = groupRepo.getGroupById(gid)
        group?.let {
            val event: Event =
                Event(
                    nextSchedule = eventDto.nextSchedule,
                    name = eventDto.name,
                    rule = eventDto.rule,
                    code = createCode(),
                    group = group,
                    attendances = mutableListOf()
                )
            eventRepo.save(event)
        } ?: run {
            print("일어나면 안되는 일이다.")
        }
    }

    private fun createCode(): String {
        val charset = "ABCDEF0123456789" // Define the character set to choose from
        val length = 4 // String 길이

        val randomString = (1..length)
            .map { charset.random() }
            .joinToString("")

        return randomString
    }


    public fun modifyEvent(eventDto: EventDto) {
        var event: Event? = eventRepo.getEventById(eventDto.id)
        event?.apply {
            name = eventDto.name
            nextSchedule = eventDto.nextSchedule
            rule = eventDto.rule
        }

        event?.let {
            eventRepo.save(event)

        } ?: {
            print("이 에러는 나오면 안됩니다.")
        }

    }

    public fun deleteEvent(eventId: Long) {
        var event: Event? = eventRepo.getEventById(eventId)

        event?.let {
            eventRepo.delete(event)

        } ?: {
            print("이 에러는 나오면 안됩니다.")
        }

    }

    public fun getEventAttend(eventId: Long): List<Attendance> {
        return attendanceRepo.findAllByEventId(eventId)

    }
    public fun checking(eid: Long, uid: Long, code: String): String {
        var stat: String = "wrong"

        val codeReturns: List<String> = eventRepo.getCodeById(eid)
        if(codeReturns.size == 1){
            if(codeReturns[0] == code){
                var attendance: Attendance? = attendanceRepo.getByUserId(uid)
                attendance?.let{
                    attendance.attend = 3
                    attendanceRepo.save(attendance)
                    stat = "success"
                } ?:{
                    stat = "attendance not found"
                    //TODO
                }
            }

        } else if(codeReturns.size == 0){
            stat = "event not found"
            //TODO
        } else{
            //TODO 이벤트 중복 핸들링
            stat = "SERIOUS ERR"
        }
        return stat
    }
    public fun requestAttend(eid: Long, uid: Long, comment: String): String {
        var stat: String = "wrong"

        val attendance: Attendance? = attendanceRepo.getByUserId(uid)
            attendance?.let{
                if(attendance.attend == 1){
                    attendance.attend = 2
                    attendanceRepo.save(attendance)
                    stat = "success"

                }else{
                    attendanceRepo.save(attendance)
                    stat = "already attends"

                }
                } ?:{
                    stat = "attendance not found"
                    //TODO
                }

        return stat
    }
    public fun confirmAttend(eid: Long, uid: Long, attend: Int): String{
        var stat: String = ""
        val attendance: Attendance? = attendanceRepo.getByUserId(uid)
            attendance?.let{
                if(attendance.attend == 2){
                    attendance.attend = attend
                    attendanceRepo.save(attendance)
                    stat = "confirm to $attend"

                }else{
                    attendanceRepo.save(attendance)
                    stat = "error - attend!=2"

                }
                } ?:{
                    //TODO
                stat = "attendance not found: NEVER"
                }

        return stat
    }


}