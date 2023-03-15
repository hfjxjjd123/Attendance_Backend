package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.repository.SdjEventRepo
import com.proj252.AIstopwatch.proj252.repository.SdjGroupRepo
import com.proj252.AIstopwatch.proj252.repository.SdjRelatedGroupRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GroupService {
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

    //TODO controller에서 Event를 받을 때 null이면 없다는 신호를 사용자에게 보내줘야한다.
    public fun getEvents(groupId: Long): List<Event> {

        return try {
            eventRepo.findEventByGroupId(groupId)
        } catch (e: Exception) {
            print("stopwatch get time err, retry?")
            listOf()
        }

    }

    public fun createGroup(userId: Long, groupName: String){

        //Entity로 설정한 객체 - 초기화하는법?
        val group: Group = Group(-1, groupName, null, mutableListOf(), mutableListOf())

        groupRepo.save(group)
    }

    public fun getNotification(gid: Long): String{
        var notification: String

        try {
            notification = groupRepo.getNotification(gid)?:""
        } catch (e: Exception) {
            notification = ""
            //TODO error handling
        }
        return notification
    }

    public fun getGroupById(gid: Long): Group?{
        val group: Group?

        group = groupRepo.getGroupById(gid)
        if(group==null){
            print("Group이 존재하지 않습니다")
            //TODO error handling
        }

        return group
    }
}