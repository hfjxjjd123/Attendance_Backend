package com.proj252.AIstopwatch.proj252.controller

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.dto.user.UserGroupsDto
import com.proj252.AIstopwatch.proj252.service.EventService
import com.proj252.AIstopwatch.proj252.service.GroupService
import org.springframework.beans.factory.annotation.Autowired
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

    @GetMapping("/event")
    @ResponseBody
    fun getGroupEvents(@RequestParam gid: Long): List<Event> {

        return groupService.getEvents(gid)

        //통신시 List를 통째로 보내고 client-side에서 해당 메시지(Stinrg)을 객체로 파싱하는 과정을 거쳐야한다.
    }
}