package com.proj252.AIstopwatch.proj252.repository

import com.proj252.AIstopwatch.proj252.domain.TmpReport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SDJStopwatchRepo: JpaRepository<TmpReport,Long>,StopwatchRepo {

    //get by userId
    @Override
    fun findTmpReportByUser_UserIdAndDate(userId: Long, date: Date): Optional<TmpReport>
    @Override
    fun saveTmpReport(tmpReport: TmpReport)


//    @Query("UPDATE TmpReport SET total_time = :second where date = :userId")


//    override fun createReport(userId: Long, date: Date): TmpReport {
//        TODO("Not yet implemented")
//    }
//
//    override fun deleteReport(userId: Long) {
//        TODO("Not yet implemented")
//    }

}