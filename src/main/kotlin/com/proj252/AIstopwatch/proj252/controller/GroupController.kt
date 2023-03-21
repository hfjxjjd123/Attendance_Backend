package com.proj252.AIstopwatch.proj252.controller

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.dto.event.EventDto
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
    @PostMapping("/create")
    @ResponseBody
    fun createGroup(@RequestBody groupCreateDto: GroupCreateDto): ResponseEntity<String> {

        try {
            groupService.createGroup(groupCreateDto.userId, groupCreateDto.name)
            return ResponseEntity.ok("success")
        }catch (e: Exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }
    @PostMapping("/{gid}/invite")
    @ResponseBody
    fun addUser(@PathVariable gid: Long, @RequestBody uid: Long): ResponseEntity<String> {

        try {
            groupService.addUser2Group(gid, uid)
            return ResponseEntity.ok("success")
        }catch (e: Exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }

    @PostMapping("/{gid}/remove")
    @ResponseBody
    fun removeUser(@PathVariable gid: Long, @RequestBody uid: Long): ResponseEntity<String> {

        try {
            groupService.removeUser2Group(gid, uid)
            return ResponseEntity.ok("success")
        }catch (e: Exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }


    //여기서 부턴 Event와 연관되어있습니다.

    @GetMapping("/event")
    @ResponseBody
    fun getRecentGroupEvent(@RequestParam gid: Long): Event? {

        return eventService.getRecentEventByGroup(gid)
    }


    @PostMapping("/event/create")
    @ResponseBody
    fun createEvent(@RequestBody eventDto: EventDto): ResponseEntity<String> {

        try {
            eventService.createEvent(eventDto)
            return ResponseEntity.ok("success")
        }catch (e: Exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }

}