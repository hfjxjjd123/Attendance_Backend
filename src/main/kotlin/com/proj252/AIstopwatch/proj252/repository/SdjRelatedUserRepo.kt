package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.domain.RelatedGroup
import com.proj252.AIstopwatch.proj252.domain.RelatedUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SdjRelatedUserRepo: JpaRepository<RelatedUser, Long> {

    fun getByUserUid(userUid: Long): RelatedUser?
    fun findAllByGroupId(groupId: Long): List<RelatedUser>
    fun getRecentAttendsByGroupIdAndUserUid(groupId: Long, userUid: Long): Int?


    //쿼리문로직 1
    //userId => groupIds => groupId
}