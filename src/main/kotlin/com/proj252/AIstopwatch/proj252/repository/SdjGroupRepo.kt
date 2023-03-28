package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SdjGroupRepo: JpaRepository<Group, Long> {

    //사용자가 그룹을 생성할 수 있다.
    //Related Group
    //Group동시 생성

    @Query("SELECT g.notification FROM Group g WHERE g.id = :id")
    fun getNotification(id: Long): List<String>
    fun getGroupById(id: Long): Group?

}