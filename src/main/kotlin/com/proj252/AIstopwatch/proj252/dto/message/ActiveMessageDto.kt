package com.proj252.AIstopwatch.proj252.dto.message

data class ActiveMessageDto(
    val sender: Long,
    val receiver: Long,
    val gid: Long,
    val stat: String
)
