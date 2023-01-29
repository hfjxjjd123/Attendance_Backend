package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Report
import org.springframework.stereotype.Repository
import java.util.*
import jakarta.persistence.EntityManager

public class JpaReportRepo:ReportRepo  {
    private val em: EntityManager

    //intellij 오류로 추정
    public constructor(em: EntityManager){
        this.em = em
    }


    override fun runStopwatch(userId: Long, date: Date) {
        TODO("Not yet implemented")
    }

    override fun pauseStopwatch(userId: Long, date: Date) {
        TODO("Not yet implemented")
    }

    override fun saveStopwatch(userId: Long, second: Int) {
        TODO("Not yet implemented")
    }

    override fun getStopwatch(userId: Long): Int {
        TODO("Not yet implemented")
    }

    override fun warnStopwatch(userId: Long, date: Date) {
        TODO("Not yet implemented")
    }

    override fun getReport(userId: Long, date: Date): Report? {
        var report: Report = em.find(Report, primaryKey= userId)
    }

    override fun createReport(userId: Long, date: Date): Report {
        TODO("Not yet implemented")
    }

    override fun getMonthlyReport(userId: Long, year: Int, month: Int): List<Report?> {
        TODO("Not yet implemented")
    }

}