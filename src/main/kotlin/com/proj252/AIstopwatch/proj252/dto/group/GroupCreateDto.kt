package com.proj252.AIstopwatch.proj252.dto.group

import com.proj252.AIstopwatch.proj252.domain.Event
import java.util.Date
import java.util.SimpleTimeZone

data class GroupCreateDto(
    val userId: Long,
    val name: String,
    val username: String
)

