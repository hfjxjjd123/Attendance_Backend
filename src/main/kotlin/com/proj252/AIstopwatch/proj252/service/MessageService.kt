package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.domain.Message
import com.proj252.AIstopwatch.proj252.domain.User
import com.proj252.AIstopwatch.proj252.dto.message.ActiveMessageDto
import com.proj252.AIstopwatch.proj252.dto.message.EventMessageDto
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

    public fun sendPublicMessage(gid: Long, publicMessageDto: PublicMessageDto){
        var user: User?
        var message: Message

        for(uid in publicMessageDto.uidList){
            user = userRepo.getUserByUid(uid)
            user?. let {
                message = Message(groupId = gid, type = publicMessageDto.stat, time = LocalDateTime.now(), user = user)
                messageRepo.save(message)
            }?:{
                print("NO such user: NEVER send")
            }
        }
    }

    public fun sendActiveMessage(gid: Long, activeMessageDto: ActiveMessageDto){
        var message: Message

        var user: User? = userRepo.getUserByUid(activeMessageDto.receiver)
        user?. let{
            message = Message(groupId = gid, type = activeMessageDto.stat, time = LocalDateTime.now(), user = user)
            messageRepo.save(message)
        }?:{
            print("NO such user: NEVER send")
        }

    }
    public fun sendPassiveMessage(gid: Long, passiveMessageDto: PassiveMessageDto){
        var message: Message

        var user: User? = userRepo.getUserByUid(passiveMessageDto.uid)
        user?. let{
            message = Message(groupId = gid, type = passiveMessageDto.stat, time = LocalDateTime.now(), user = user)
            messageRepo.save(message)
        }?:{
            print("NO such user: NEVER send")
        }

    }
    public fun sendEventMessage(eid: Long, eventMessageDto: EventMessageDto){
        var message: Message

        var user: User? = userRepo.getUserByUid(eventMessageDto.receiver)
        user?. let{
            //!!gid가 아닌 eid의 값이 전송되게된다.
            message = Message(groupId = eid, type = eventMessageDto.stat, time = LocalDateTime.now(), user = user)
            messageRepo.save(message)
        }?:{
            print("NO such user: NEVER send")
        }
    }


    //!!TODO 이후처리하기
    public fun sendPublicEventMessage(gid: Long, publicEventMessageDto: PublicEventMessageDto){
        var user: User?
        var message: Message

        for(uid in publicEventMessageDto.uidList){
            user = userRepo.getUserByUid(uid)
            user?. let {
                message = Message(groupId = gid, type = publicEventMessageDto.stat, time = LocalDateTime.now(), user = user)
                messageRepo.save(message)
            }?:{
                print("NO such user: NEVER send")
            }
        }
    }


}