package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SdjUserRepo: JpaRepository<User,Long> {

    fun getUserById(uid: Long): User?

}