package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.RelatedUser
import com.proj252.AIstopwatch.proj252.dto.group.AttendsProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SdjRelatedUserRepo: JpaRepository<RelatedUser, Long> {

    fun getByUserUid(userUid: Long): RelatedUser?
    fun getRecentAttendsByGroupIdAndUserUid(groupId: Long, userUid: Long): Int?

    fun findRelatedUserByGroupId(gid: Long): List<Long>

    @Query("SELECT ru.username AS username, ru.recentAttends AS recentAttends FROM RelatedUser ru WHERE ru.group.id = :gid")
    fun getAttendanceByGroupId(@Param("gid") gid: Long): List<AttendsProjection>?

    //쿼리문로직 1
    //userId => groupIds => groupId
}
