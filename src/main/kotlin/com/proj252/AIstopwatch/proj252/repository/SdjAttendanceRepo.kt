package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Attendance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SdjAttendanceRepo: JpaRepository<Attendance, Long> {

    fun findAllByEventId(eventId: Long): List<Attendance>

    fun getByUserId(userId: Long): Attendance?

}