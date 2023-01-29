package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.Report
import java.util.Date

public interface ReportRepo {

    fun runStopwatch(userId: Long, date: Date): Unit
    fun pauseStopwatch(userId: Long, date: Date): Unit
    fun saveStopwatch(userId: Long, second: Int): Unit
    fun getStopwatch(userId: Long): Int
    fun warnStopwatch(userId: Long, date: Date): Unit

    fun getReport(userId: Long, date: Date): Report?
    fun createReport(userId: Long, date: Date): Report

    //!! Report를 받아올지 공부시간을 int로 받아올 지 생각
    fun getMonthlyReport(userId: Long, year: Int, month: Int): List<Report?>
}