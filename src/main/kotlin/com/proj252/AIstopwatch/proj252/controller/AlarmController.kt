package com.proj252.AIstopwatch.proj252.controller

import com.proj252.AIstopwatch.proj252.dto.stopwatch.StopwatchPauseDto
import com.proj252.AIstopwatch.proj252.dto.stopwatch.StopwatchRunDto
import com.proj252.AIstopwatch.proj252.dto.stopwatch.StopwatchSyncDto
import com.proj252.AIstopwatch.proj252.service.StopwatchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("alarm")
class AlarmController {
    @PostMapping("/setting")
    fun setAlarm(@RequestBody dto:dto, @CookieValue userId: Long){
        alarmService.saveStopwatch(userId, stopwatchSyncDto.date, stopwatchSyncDto.time)
    }
    @GetMapping
    fun getAlarm(@CookieValue userId: Long): Int{
        return alarmService.getTotalTime(userId, Date())
    }

}
