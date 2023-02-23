package com.proj252.AIstopwatch.proj252.u_dto.stopwatch

import java.util.Date

data class StopwatchPauseDto(
    val date: Date,
    var pauseTime: Date
)