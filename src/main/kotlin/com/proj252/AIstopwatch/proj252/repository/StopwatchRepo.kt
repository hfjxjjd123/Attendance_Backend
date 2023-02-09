package com.proj252.AIstopwatch.proj252.repository
import com.proj252.AIstopwatch.proj252.domain.ChangeTime
import com.proj252.AIstopwatch.proj252.domain.TmpReport
import com.proj252.AIstopwatch.proj252.domain.WarnTime
import org.springframework.stereotype.Repository
import java.util.Date

public interface StopwatchRepo {
    fun getByUserId(userId: Long): TmpReport?
    fun getTotalTime(userId: Long): Int
    fun setTotalTime(userId: Long, second: Int)



//    fun deleteReport(userId: Long)
//    fun createReport(userId: Long, date: Date): TmpReport
}