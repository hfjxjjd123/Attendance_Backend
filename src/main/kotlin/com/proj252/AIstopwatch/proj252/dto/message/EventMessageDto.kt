package com.proj252.AIstopwatch.proj252.dto.message

data class EventMessageDto(
    val sender: Long,
    val receiver: Long,
    val eid: Long,
    val stat: String
)
