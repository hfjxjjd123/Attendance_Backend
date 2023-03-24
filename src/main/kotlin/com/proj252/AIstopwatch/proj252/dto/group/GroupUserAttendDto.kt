package com.proj252.AIstopwatch.proj252.dto.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupUserAttendDto(
    @SerialName("user") val username: String,
    @SerialName("nextAttend") val nextAttend: Int
)