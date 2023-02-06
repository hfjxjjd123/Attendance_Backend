package com.proj252.AIstopwatch.proj252.repository
import com.proj252.AIstopwatch.proj252.domain.ChangeTime
import com.proj252.AIstopwatch.proj252.domain.TmpReport
import com.proj252.AIstopwatch.proj252.domain.WarnTime
import java.util.Date

public interface StopwatchRepo {
    fun getByUserId(userId: Long): TmpReport?
    fun getTotalTime(userId: Long): Int
    fun setTotalTime(userId: Long, second: Int)
    fun setRunTime(userId: Long, datetime: Date)
    fun getChangedTime(userId: Long): List<ChangeTime>
    fun setPauseTime(userId: Long, datetime: Date)
    fun setWarnTime(userId: Long, datetime: Date)
    fun getWarnTime(userId: Long): List<WarnTime>


    fun deleteReport(userId: Long)
    fun createReport(userId: Long, date: Date): TmpReport
}