package com.proj252.AIstopwatch.proj252.controller

import com.proj252.AIstopwatch.proj252.domain.Attendance
import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.dto.event.CheckDto
import com.proj252.AIstopwatch.proj252.dto.event.ConfirmAttendDto
import com.proj252.AIstopwatch.proj252.dto.event.EventDto
import com.proj252.AIstopwatch.proj252.dto.event.RequestAttendDto
import com.proj252.AIstopwatch.proj252.dto.group.*
import com.proj252.AIstopwatch.proj252.service.EventService
import com.proj252.AIstopwatch.proj252.service.GroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class EventController {

    private final var eventService: EventService

    @Autowired
    constructor(eventService: EventService) {
        this.eventService = eventService
    }

    @GetMapping("{eid}/attends")
    @ResponseBody
    fun getAttendsNow(@PathVariable gid: Long, @PathVariable eid: Long): List<Attendance> {
        return eventService.getEventAttend(eid)
    }

    @PostMapping("{eid}/checking")
    @ResponseBody
    fun checking(@PathVariable eid: Long, @RequestBody checkDto: CheckDto): ResponseEntity<String> {
        val stat: String = eventService.checking(code = checkDto.code, eid = eid, uid = checkDto.userId)
        if (stat == "success") {
            return ResponseEntity.ok(stat)
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stat)
        }
    }
    @PostMapping("{eid}/request-attend")
    @ResponseBody
    fun requestAttend(@PathVariable eid: Long, @RequestBody requestAttendDto: RequestAttendDto): ResponseEntity<String> {
        val stat: String = eventService.requestAttend(eid, requestAttendDto.userId, requestAttendDto.comment)
        if (stat == "success") {
            return ResponseEntity.ok(stat)
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stat)
        }
    }
    @PostMapping("{eid}/confirm-attend")
    @ResponseBody
    fun confirmAttend(@PathVariable eid: Long, @RequestBody confirmAttendDto: ConfirmAttendDto): ResponseEntity<String> {
        val stat: String = eventService.confirmAttend(eid, confirmAttendDto.userId, confirmAttendDto.attend)
        if (stat == "success") {
            return ResponseEntity.ok(stat)
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stat)
        }
    }


}