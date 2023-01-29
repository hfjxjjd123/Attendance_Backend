package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Report
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SdjReportRepo: JpaRepository<Report,Long>,ReportRepo {

    @Override
    override fun runStopwatch(userId: Long, date: Date)

    @Override
    override fun pauseStopwatch(userId: Long, date: Date)

    //...

}