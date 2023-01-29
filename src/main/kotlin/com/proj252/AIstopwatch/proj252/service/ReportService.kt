package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.domain.Report
import com.proj252.AIstopwatch.proj252.repository.JpaReportRepo
import com.proj252.AIstopwatch.proj252.repository.ReportRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Date

@Transactional
class ReportService {

    private lateinit var reportRepo: ReportRepo

    //여기서 repo 갈아끼울 수 있음
    constructor(reportRepo: ReportRepo){
        this.reportRepo = reportRepo
    }

    public fun showReport(userId: Long, date: Date):Report{

        var report:Report? = reportRepo.getReport(userId = userId,date = date)
        if(report == null){
            report = reportRepo.createReport(userId = userId, date = date)
        }

        return report
    }

    //!! 일단 내가 생각하는 이미지는 달력에서 공부시간만 띄워주는 것이므로, 공부시간(Int)만 띄우면 된다고 생가갛ㅁ
    public fun showMonthlyReport(userId: Long, year: Int, month: Int):List<Int>{
        var reports: List<Report?> = reportRepo.getMonthlyReport(userId=userId, year=year, month=month)
        var monthlyReports: MutableList<Int> = mutableListOf()

        for(report:Report? in reports){
            if(report == null){
                monthlyReports.add(0)
            }else{
                monthlyReports.add(report.totalTimes)
            }
        }

        return monthlyReports
    }

}