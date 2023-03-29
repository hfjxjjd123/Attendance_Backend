package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.domain.RelatedGroup
import com.proj252.AIstopwatch.proj252.domain.RelatedUser
import com.proj252.AIstopwatch.proj252.dto.group.GroupUserAttendsDto
import com.proj252.AIstopwatch.proj252.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GroupService {
    //GroupService에서는 Group Repo만 불러오게 처리를 해주자
    private var eventRepo: SdjEventRepo
    private var groupRepo: SdjGroupRepo
    private var relatedGroupRepo: SdjRelatedGroupRepo
    private var relatedUserRepo: SdjRelatedUserRepo
    private var userRepo: SdjUserRepo

    @Autowired
    //여기서 repo 갈아끼울 수 있음
    constructor(
        eventRepo: SdjEventRepo,
        groupRepo: SdjGroupRepo,
        relatedGroupRepo: SdjRelatedGroupRepo,
        relatedUserRepo: SdjRelatedUserRepo,
        userRepo: SdjUserRepo,
    ) {
        this.eventRepo = eventRepo
        this.groupRepo = groupRepo
        this.relatedGroupRepo = relatedGroupRepo
        this.relatedUserRepo = relatedUserRepo
        this.userRepo = userRepo
    }

    //TODO controller에서 Event를 받을 때 null이면 없다는 신호를 사용자에게 보내줘야한다.
    public fun getEvents(groupId: Long): List<Event> {

        return try {
            eventRepo.findAllByGroupId(groupId)
        } catch (e: Exception) {
            print("stopwatch get time err, retry?")
            listOf()
        }

    }

    public fun createGroup(userId: Long, groupName: String, username: String){

        //TODO 부분적 초기화해야함
        var group: Group =
            Group(name = groupName, notification = "")

        val relatedUser: RelatedUser = RelatedUser(userId, 3, username ,null, group)

        val savedGroup = groupRepo.save(group)
        relatedUserRepo.save(relatedUser)


        savedGroup.id?.let {
            val user = userRepo.getUserByUid(userId)

            if(user != null){
                val relatedGroup: RelatedGroup = RelatedGroup(it, role = 3, user)
                relatedGroupRepo.save(relatedGroup)
            }else{
                print("this does not happen!")
            }
        } ?: run {
            print("this does not happen!")
        }

    }

    public fun getNotification(gid: Long): String {
        val notification: List<String> = groupRepo.getNotification(gid)

        if(notification.size == 1){
            return notification[0]
        }else {
            return ""
        }
    }

    public fun getGroup(gid: Long): Group? {
        val group: Group?

        group = groupRepo.getGroupById(gid)

        if (group == null) {
            print("serious error")
        }

        return group
    }

    public fun addUser2Group(gid: Long, uid: Long, username: String){

        //TODO 2개의 save를 한 트랜잭션화 하는 과정이 필요하다.

        val group = groupRepo.getGroupById(gid)
        //group이 존재할 때
        if(group != null){
            //RelatedUser에 사람을 추가한다.
            val relatedUser = RelatedUser(uid, 2, username,0, group)
            relatedUserRepo.save(relatedUser)
        }else{
            print("serious - group not exist")
        }

        val user = userRepo.getUserByUid(uid)
        if(user != null){
            val relatedGroup = RelatedGroup(gid, 2, user)
            relatedGroupRepo.save(relatedGroup)
        }else{
            print("serious - user not exist")
        }
    }
    public fun removeUser2Group(gid: Long, uid: Long){

        //TODO 2개의 save를 한 트랜잭션화 하는 과정이 필요하다.
        val relatedUser: List<RelatedUser> = relatedUserRepo.getByUserUid(gid, uid)
        if(relatedUser.size == 1){
            relatedUserRepo.delete(relatedUser[0])
        }

        val relatedGroup: List<RelatedGroup> = relatedGroupRepo.getRelatedGroupByUserUid(gid, uid)
        if(relatedGroup.size == 1){
            relatedGroupRepo.delete(relatedGroup[0])
        }
    }

    public fun delegateUser(gid: Long, receiverId: Long, senderId: Long){
        val receiver: List<RelatedUser>
        val receiverGroup: List<RelatedGroup>

        val sender: List<RelatedUser> = relatedUserRepo.getByUserUid(gid, senderId)
        if(sender.size == 1){
            if(sender[0].role==1){
                receiver = relatedUserRepo.getByUserUid(gid, receiverId)
                receiverGroup = relatedGroupRepo.getRelatedGroupByUserUid(receiverId, gid)
                if((receiver.size == 1) && (receiverGroup.size == 1)){
                    receiver[0].role = 1
                    receiverGroup[0].role = 1
                    relatedUserRepo.save(receiver[0])
                    relatedGroupRepo.save(receiverGroup[0])
                }else{
                    print("SOMETHIING WRONG: NEVER")
                }

            }else{
                print("NO AUTHORIZED: ${sender[0].username}")
            }
        }
    }
    public fun degradeUser(gid: Long, uid: Long){

        val host: List<RelatedUser> = relatedUserRepo.getByUserUid(gid, uid)
        val hostGroup: List<RelatedGroup> = relatedGroupRepo.getRelatedGroupByUserUid(gid = gid, uid = uid)

        if((host.size == 1) && (host.size == 1)){
            host[0].role = 0
            hostGroup[0].role = 0
            relatedUserRepo.save(host[0])
            relatedGroupRepo.save(hostGroup[0])
        }else{
            print("SOMETHIING WRONG: NEVER")
        }

    }


    //related를 다루게 됩니다.
    public fun getMyAttends(gid: Long, uid: Long): Int{
        val attendResponse: List<Int> = relatedUserRepo.getAttendsLogByGroupIdAndUserUid(gid, uid)

        if(attendResponse.size == 1){
            return attendResponse[0]
        }else {
            return -1
        }
    }

    public fun getGroupAttends(gid: Long): List<GroupUserAttendsDto>{
        val attends: List<GroupUserAttendsDto>? = relatedUserRepo.getRecentAttendsByGroupId(gid)
        attends?.let {
            return attends
        }?: kotlin.run {
            print("그룹이 존재하지 않는다. 일어나면 안되는 일")
            return listOf()
        }
    }

}