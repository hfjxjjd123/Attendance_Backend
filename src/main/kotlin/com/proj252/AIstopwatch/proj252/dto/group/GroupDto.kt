package com.proj252.AIstopwatch.proj252.dto.group

import com.proj252.AIstopwatch.proj252.domain.Event
import java.util.Date
import java.util.SimpleTimeZone

data class GroupDto(
    val id: Long,
    val name: String,
    val notification: String,
    val event: Event?
)
