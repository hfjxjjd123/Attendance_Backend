package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.repository.JpaReportRepo
import com.proj252.AIstopwatch.proj252.repository.ReportRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class StopwatchService {
    private lateinit var reportRepo: ReportRepo

    @Autowired
    //여기서 repo 갈아끼울 수 있음
    constructor(reportRepo: JpaReportRepo){
        this.reportRepo = reportRepo
    }

    public fun runStopwatch(userId: Long, date: Date){
        try{
            reportRepo.runStopwatch(userId= userId, date= date)
        }catch (e: Exception){
            //!! 알아서 처리
            print("stopwatch run err, retry?")
        }
    }
    public fun pauseStopwatch(userId: Long, date: Date){
        try{
            reportRepo.pauseStopwatch(userId= userId, date= date)
        }catch (e: Exception){
            //!! 알아서 처리
            print("stopwatch pause err, retry?")
        }
    }
    public fun warnStopwatch(userId: Long, date: Date){
        try{
            reportRepo.warnStopwatch(userId= userId, date= date)
        }catch (e: Exception){
            //!! 알아서 처리
            print("stopwatch warn err, retry?")
        }
    }


    public fun saveStopwatch(userId: Long, time: Int){
        try {
            reportRepo.saveStopwatch(userId = userId, second = time)
        }catch (e: Exception){
            //!! 알아서 처리
            print("stopwatch save err, retry?")
        }
    }
    public fun getStopwatch(userId: Long): Int{
        try {
            return reportRepo.getStopwatch(userId)
        }catch (e: Exception){
            print("stopwatch get err")
        }

        return 0
    }






}