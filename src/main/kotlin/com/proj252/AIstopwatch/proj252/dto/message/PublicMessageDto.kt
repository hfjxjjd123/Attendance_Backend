package com.proj252.AIstopwatch.proj252.dto.message

data class PublicMessageDto(

    val gid: Long,
    val uidList: List<Long>,
    val stat: String,
    val body: String?,

)