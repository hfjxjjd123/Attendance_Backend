package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.domain.Message
import com.proj252.AIstopwatch.proj252.domain.User
import com.proj252.AIstopwatch.proj252.dto.message.ActiveMessageDto
import com.proj252.AIstopwatch.proj252.dto.message.PublicMessageDto
import com.proj252.AIstopwatch.proj252.dto.message.PassiveMessageDto
import com.proj252.AIstopwatch.proj252.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class MessageService {
    private var messageRepo: SdjMessageRepo
    private var userRepo: SdjUserRepo
    private var relatedUserRepo: SdjRelatedUserRepo
    private var groupRepo: SdjGroupRepo

    @Autowired
    //여기서 repo 갈아끼울 수 있음
    constructor(
        messageRepo: SdjMessageRepo,
        userRepo: SdjUserRepo,
        relatedUserRepo: SdjRelatedUserRepo,
        groupRepo: SdjGroupRepo

    ) {
        this.messageRepo = messageRepo
        this.userRepo = userRepo
        this.relatedUserRepo = relatedUserRepo
        this.groupRepo = groupRepo
    }

    public fun sendNotify(gid: Long, notifyMessageDto: PublicMessageDto){

        var user: User?
        var message: Message

        for(uid in notifyMessageDto.uidList){
            user = userRepo.getUserByUid(uid)
            user?. let {
                message = Message(groupId = gid, type = notifyMessageDto.stat, time = LocalDateTime.now(), user = user)
                messageRepo.save(message)
            }?:{
                print("NO such user: NEVER send")
            }
        }

        var group: Group? = groupRepo.getGroupById(gid)
        group?.let {
            group.notification = notifyMessageDto.body
            groupRepo.save(group)
        }?: run{
            "group not exist error: NEVER"
        }

    }

    public fun sendActiveMessage(gid: Long, activeMessageDto: ActiveMessageDto){
        var user: User?
        var message: Message

        user = userRepo.getUserByUid(activeMessageDto.receiver)
        user?. let{
            message = Message(groupId = gid, type = activeMessageDto.stat, time = LocalDateTime.now(), user = user)
            messageRepo.save(message)
        }?:{
            print("NO such user: NEVER send")
        }

    }
    public fun sendPassiveMessage(gid: Long, passiveMessageDto: PassiveMessageDto){
        val users: List<Long> = relatedUserRepo.getUserIdsByGroupId(gid)
        var message: Message
        var user: User?

        for(uid in users){
            user = userRepo.getUserByUid(uid)
                user?.let {
                message = Message(groupId = gid, type = passiveMessageDto.stat, time = LocalDateTime.now(), user = user)
                messageRepo.save(message)
            } ?: run{
                print("User is not Exist: NOT DONE")
            }
        }

    }
}