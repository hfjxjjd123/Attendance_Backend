package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.RelatedUser
import com.proj252.AIstopwatch.proj252.dto.group.GroupUserAttendDto
import com.proj252.AIstopwatch.proj252.dto.group.GroupUserAttendsDto
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

    @Query("SELECT ru.username AS username, ru.attendsLog AS recentAttends FROM RelatedUser ru WHERE ru.group.id = :gid")
    fun getRecentAttendsByGroupId(@Param("gid") gid: Long): List<GroupUserAttendsDto>?

    @Query("SELECT ru.username AS username, ru.nextAttend AS nextAttend FROM RelatedUser ru WHERE ru.group.id = :gid")
    fun getNextAttendsByGroupId(@Param("gid") gid: Long): List<GroupUserAttendDto>?



    //쿼리문로직 1
    //userId => groupIds => groupId
}
