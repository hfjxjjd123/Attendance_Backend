package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.User
import com.proj252.AIstopwatch.proj252.repository.SdjEventRepo
import com.proj252.AIstopwatch.proj252.repository.SdjGroupRepo
import com.proj252.AIstopwatch.proj252.repository.SdjRelatedGroupRepo
import com.proj252.AIstopwatch.proj252.repository.SdjUserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    private var userRepo: SdjUserRepo

    @Autowired
    //여기서 repo 갈아끼울 수 있음
    constructor(userRepo: SdjUserRepo) {
        this.userRepo = userRepo
    }

    public fun modifyUsername(uid: Long, newName: String): String? {
        var user: User?
        var changedName: String? = null

        try {
            user = userRepo.getUserById(uid)

            user?.let {
                it.name = newName
                userRepo.save(it)
                changedName = it.name
            }

        } catch (e: Exception) {
            //TODO Notifying Message of Failed
        }
        return changedName
    }

}