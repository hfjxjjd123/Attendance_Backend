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

    @Query("SELECT ru FROM RelatedUser ru WHERE ru.group.id = :groupId AND ru.userUid = :userUid")
    fun getByUserUid(groupId: Long, userUid: Long):List<RelatedUser>

    @Query("SELECT ru.attendsLog FROM RelatedUser ru WHERE ru.group.id = :groupId AND ru.userUid = :userUid")
    fun getAttendsLogByGroupIdAndUserUid(groupId: Long, userUid: Long): List<Int>

    @Query("SELECT ru.username AS username, ru.attendsLog AS recentAttends FROM RelatedUser ru WHERE ru.group.id = :gid")
    fun getRecentAttendsByGroupId(@Param("gid") gid: Long): List<GroupUserAttendsDto>

    @Query("SELECT ru.userUid AS userUid FROM RelatedUser ru where ru.group.id = :gid AND ru.role = 3")
    fun getUserIdsByGroupId(@Param("gid") gid:Long): List<Long>


    //쿼리문로직 1
    //userId => groupIds => groupId
}
