package com.proj252.AIstopwatch.proj252.controller

import com.proj252.AIstopwatch.proj252.domain.Attendance
import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.dto.event.EventDto
import com.proj252.AIstopwatch.proj252.dto.group.*
import com.proj252.AIstopwatch.proj252.dto.message.ActiveMessageDto
import com.proj252.AIstopwatch.proj252.dto.message.PublicMessageDto
import com.proj252.AIstopwatch.proj252.dto.message.PassiveMessageDto
import com.proj252.AIstopwatch.proj252.service.EventService
import com.proj252.AIstopwatch.proj252.service.GroupService
import com.proj252.AIstopwatch.proj252.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class GroupController {

    private final var eventService: EventService
    private final var groupService: GroupService
    private final var messageService: MessageService

    @Autowired
    constructor(eventService: EventService, groupService: GroupService, messageService: MessageService) {
        this.eventService = eventService
        this.groupService = groupService
        this.messageService = messageService
    }

    @GetMapping("{gid}/events")
    @ResponseBody
    fun getGroupEvents(@PathVariable gid: Long): List<Event> {

        return groupService.getEvents(gid)

        //통신시 List를 통째로 보내고 client-side에서 해당 메시지(Stinrg)을 객체로 파싱하는 과정을 거쳐야한다.
    }


    @GetMapping("{gid}")
    @ResponseBody
    fun getGroup(@PathVariable gid: Long): ResponseEntity<Any> {

        var groupDto: GroupDto? = null

        val group: Group? = groupService.getGroup(gid)
        group?.let {
            val event: Event? = eventService.getRecentEventByGroup(gid)
            groupDto = GroupDto(group.id, group.name, group.notification ?: "", event)
        }

        return if (groupDto != null) {
            ResponseEntity.ok(groupDto)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Group not found")
        }
    }

    @PostMapping("create-group")
    @ResponseBody
    fun createGroup(@RequestBody groupCreateDto: GroupCreateDto): ResponseEntity<String> {

        try {
            groupService.createGroup(groupCreateDto.userId, groupCreateDto.name, groupCreateDto.username)
            return ResponseEntity.ok("success")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }

    @PostMapping("{gid}/join")
    @ResponseBody
    fun addUser(@PathVariable gid: Long, @RequestBody groupJoinDto: GroupJoinDto): ResponseEntity<String> {

        try {
            groupService.addUser2Group(gid, groupJoinDto.userId, groupJoinDto.username)

            val passiveMessageDto: PassiveMessageDto = PassiveMessageDto(gid, groupJoinDto.userId, "ACPT")
            messageService.sendPassiveMessage(gid, passiveMessageDto)
            return ResponseEntity.ok("success")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }

    @PostMapping("{gid}/remove")
    @ResponseBody
    fun removeUser(@PathVariable gid: Long, @RequestBody activeMessageDto: ActiveMessageDto): ResponseEntity<String> {

        try {
            groupService.removeUser2Group(gid, activeMessageDto.receiver)

            messageService.sendActiveMessage(gid, activeMessageDto)
            return ResponseEntity.ok("success - remove")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed - remove")
        }
    }
    @PostMapping("{gid}/removed")
    @ResponseBody
    fun removedUser(@PathVariable gid: Long, @RequestBody passiveMessageDto: PassiveMessageDto): ResponseEntity<String> {

        try {
            groupService.removeUser2Group(gid, passiveMessageDto.uid)

            messageService.sendPassiveMessage(gid, passiveMessageDto)
            return ResponseEntity.ok("success")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }
    @PostMapping("{gid}/delegate")
    @ResponseBody
    fun delegateUser(@PathVariable gid: Long, @RequestBody activeMessageDto: ActiveMessageDto): ResponseEntity<String> {
        //위임 선언 메시지 위임 당함 메시지 동시전송
        try {
            groupService.delegateUser(gid, activeMessageDto.receiver, activeMessageDto.sender)

            messageService.sendActiveMessage(gid, activeMessageDto)

            val passiveMessageDto = PassiveMessageDto(gid, activeMessageDto.sender, "UPGR")
            messageService.sendPassiveMessage(gid, passiveMessageDto)
            return ResponseEntity.ok("success - delegate")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed - delegate")
        }
    }
    //TODO !! 모든 uid 전송 dto 삭제, Cookie를 이용해서 전달될것임!
    @PostMapping("{gid}/degrade")
    @ResponseBody
    fun degradeUser(@PathVariable gid: Long, @CookieValue uid: Long, @RequestBody publicMessageDto: PublicMessageDto): ResponseEntity<String> {
        //위임 선언 메시지 위임 당함 메시지 동시전송
        try {
            groupService.degradeUser(gid = gid, uid = uid)

            messageService.sendPublicMessage(gid, publicMessageDto)
            return ResponseEntity.ok("success - degrade")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed - degrade")
        }
    }


    //여기서 부턴 Event와 연관되어있습니다.

    @GetMapping("{gid}/recent-event")
    @ResponseBody
    fun getRecentGroupEvent(@PathVariable gid: Long): Event? {

        return eventService.getRecentEventByGroup(gid)
    }


    @PostMapping("{gid}/create-event")
    @ResponseBody
    fun createEvent(@PathVariable gid: Long, @RequestBody eventDto: EventDto): ResponseEntity<String> {

        try {
            eventService.createEvent(eventDto, gid)
            return ResponseEntity.ok("success")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }

    @PostMapping("{gid}/change-event")
    @ResponseBody
    fun modifyEvent(@PathVariable gid: Long, @RequestBody eventDto: EventDto): ResponseEntity<String> {

        try {
            eventService.modifyEvent(eventDto)
            return ResponseEntity.ok("success")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }

    @PostMapping("{gid}/delete-event")
    @ResponseBody
    fun deleteEvent(@PathVariable gid: Long, @RequestBody eventId: Long): ResponseEntity<String> {

        try {
            eventService.deleteEvent(eventId)
            return ResponseEntity.ok("success")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed")
        }
    }
    @PostMapping("{gid}/notify")
    @ResponseBody
    fun notify(@PathVariable gid: Long, @RequestBody notifyMessageDto: PublicMessageDto): String {
        //TODO messageService.notify
        messageService.sendNotify(gid, notifyMessageDto)

        return "try it"
    }



    @GetMapping("{gid}/my-attends")
    @ResponseBody
    fun getMyAttends(@PathVariable gid: Long, @RequestBody uid: Long): Int {
        return groupService.getMyAttends(gid, uid)
    }
    @GetMapping("{gid}/group-attends")
    @ResponseBody
    fun getAttends(@PathVariable gid: Long): List<GroupUserAttendsDto> {
        return groupService.getGroupAttends(gid)
    }
    @GetMapping("{gid}/{eid}/attends")
    @ResponseBody
    fun getAttendsNow(@PathVariable gid: Long, @PathVariable eid: Long): List<Attendance> {
        return eventService.getEventAttend(eid)
    }
}