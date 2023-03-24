package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Event
import com.proj252.AIstopwatch.proj252.domain.Group
import com.proj252.AIstopwatch.proj252.domain.RelatedGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SdjRelatedGroupRepo: JpaRepository<RelatedGroup, Long> {
    fun getRelatedGroupByGroupId(gid: Long): RelatedGroup?


    @Query("SELECT rg.groupId FROM RelatedGroup rg WHERE rg.user.id = :uid AND rg.role = :role")
    fun findGroupIdByUserUidAndRole(uid: Long, role: Int): List<Long>

    //쿼리문로직 1
    //userId => groupIds => groupId
}