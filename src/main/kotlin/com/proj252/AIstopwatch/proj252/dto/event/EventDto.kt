package com.proj252.AIstopwatch.proj252.dto.event

import com.proj252.AIstopwatch.proj252.domain.Group
import jakarta.persistence.*
import java.sql.Date

data class EventDto(

    val groupId: Long,
    val nextSchedule: Date,
    val name: String,
    val rule: Int,

)