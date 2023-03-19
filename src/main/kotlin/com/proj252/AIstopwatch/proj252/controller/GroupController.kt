package com.proj252.AIstopwatch.proj252.controller

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.dto.group.GroupCreateDto
import com.proj252.AIstopwatch.proj252.dto.group.GroupDto
import com.proj252.AIstopwatch.proj252.dto.user.UserGroupsDto
import com.proj252.AIstopwatch.proj252.service.EventService
import com.proj252.AIstopwatch.proj252.service.GroupService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("group")
class GroupController {

    private final var eventService: EventService
    private final var groupService: GroupService

    @Autowired
    constructor(eventService: EventService, groupService: GroupService) {
        this.eventService = eventService
        this.groupService = groupService
    }

    @GetMapping("/events")
    @ResponseBody
    fun getGroupEvents(@RequestParam gid: Long): List<Event> {

        return groupService.getEvents(gid)

        //통신시 List를 통째로 보내고 client-side에서 해당 메시지(Stinrg)을 객체로 파싱하는 과정을 거쳐야한다.
    }

    @GetMapping("/event")
    @ResponseBody
    fun getRecentGroupEvent(@RequestParam gid: Long): Event? {

        return eventService.getRecentEventByGroup(gid)
    }


    @GetMapping
    @ResponseBody
    fun getGroup(@RequestParam gid: Long): ResponseEntity<Any> {

        var groupDto: GroupDto? = null

        val group: Group? = groupService.getGroup(gid)
        group?.let {
            val event: Event? = eventService.getRecentEventByGroup(gid)
            groupDto = GroupDto(group.id, group.name, group.notification?:"", event)
        }

        return if (groupDto != null) {
            ResponseEntity.ok(groupDto)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Group not found")
        }
    }
    @PostMapping
    @ResponseBody
    fun createGroup(@RequestBody groupCreateDto: GroupCreateDto): ResponseEntity<String> {

        try {
            groupService.createGroup(groupCreateDto.userId, groupCreateDto.name)
            return ResponseEntity.ok("success")
        }catch (e: Exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }

}