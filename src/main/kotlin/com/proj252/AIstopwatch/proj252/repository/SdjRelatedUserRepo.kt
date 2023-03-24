package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.RelatedUser
import com.proj252.AIstopwatch.proj252.dto.group.GroupUserAttendsDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SdjRelatedUserRepo: JpaRepository<RelatedUser, Long> {

    fun getByUserUid(userUid: Long): RelatedUser?

    @Query("SELECT ru.attendsLog FROM RelatedUser ru WHERE ru.group.id = :groupId AND ru.userUid = :userUid")
    fun getAttendsLogByGroupIdAndUserUid(groupId: Long, userUid: Long): List<Int>

    @Query("SELECT ru.username AS username, ru.attendsLog AS recentAttends FROM RelatedUser ru WHERE ru.group.id = :gid")
    fun getRecentAttendsByGroupId(@Param("gid") gid: Long): List<GroupUserAttendsDto>


    //쿼리문로직 1
    //userId => groupIds => groupId
}
