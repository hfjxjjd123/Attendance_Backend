package com.proj252.AIstopwatch.proj252.controller

import com.proj252.AIstopwatch.proj252.domain.Report
import com.proj252.AIstopwatch.proj252.service.ReportService
import kotlinx.serialization.json.JsonObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.HashMap

@RestController
@RequestMapping("/daily-report")
class ReportController {
    private final var reportService: ReportService

    @Autowired
    constructor(reportService: ReportService){
        this.reportService = reportService
    }

    @GetMapping
    fun getReport(@RequestParam date: String, @CookieValue userId: Long):Report{
        var map: HashMap<String,Any> = HashMap<String, Any>()
        val dateTime: Date = SimpleDateFormat("yyyyMMdd").parse(date)
        var report: Report = reportService.showReport(userId = userId, date= dateTime)

        return report
    }


}