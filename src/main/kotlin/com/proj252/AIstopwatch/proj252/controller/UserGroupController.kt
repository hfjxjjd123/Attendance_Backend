package com.proj252.AIstopwatch.proj252.controller

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.dto.user.UserGroupsDto
import com.proj252.AIstopwatch.proj252.service.EventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping
class UserGroupController {

    private final var eventService: EventService

    @Autowired
    constructor(eventService: EventService) {
        this.eventService = eventService
    }

    @GetMapping("{uid}/groups")
    @ResponseBody
    fun getInvolvingGroup(@PathVariable uid: Long): UserGroupsDto {

        return UserGroupsDto(
            hostGroupId = eventService.getGroupsByUser(uid, 3),
            partisGroupId = eventService.getGroupsByUser(uid, 2)
        )
    }
    //3=>host
    //2=>partis
    //0=>none

    @PostMapping("{uid}/events")
    @ResponseBody
    fun getRecentEvent(@PathVariable uid: Long, @RequestBody groupIds: List<Long>): List<Event> {
        return eventService.getEventsByGroups(groups = groupIds)
    }


}
