package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.repository.StopwatchRepo
import com.proj252.AIstopwatch.proj252.repository.SDJStopwatchRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class StopwatchService {
    private lateinit var stopwatchRepo: StopwatchRepo

    @Autowired
    //여기서 repo 갈아끼울 수 있음
    constructor(reportRepo: SDJStopwatchRepo){
        this.stopwatchRepo = reportRepo
    }

    public fun runStopwatch(userId: Long, date: Date): Int{
        var totalTime:Int = -1
        try{
            totalTime = stopwatchRepo.getTotalTime(userId= userId)
            stopwatchRepo.setRunTime(userId = userId, datetime = date)

        }catch (e: Exception){
            //!! 알아서 처리
            print("stopwatch run err, retry?")

            //!! 무한루프 위험
            while(totalTime != -1){
                totalTime = stopwatchRepo.getTotalTime(userId=userId)
            }
        }
        return totalTime
    }

    public fun pauseStopwatch(userId: Long, date: Date){
        try{
            stopwatchRepo.setPauseTime(userId= userId, datetime = date)
        }catch (e: Exception){
            //!! 알아서 처리
            print("stopwatch pause err, retry?")
        }
    }

    public fun warnStopwatch(userId: Long, date: Date){
        try{
            stopwatchRepo.setWarnTime(userId= userId, datetime= date)
        }catch (e: Exception){
            //!! 알아서 처리
            print("stopwatch warn err, retry?")
        }
    }

    public fun saveStopwatch(userId: Long, time: Int){
        try {
            stopwatchRepo.setTotalTime(userId = userId, second = time)
        }catch (e: Exception){
            //!! 알아서 처리
            print("stopwatch save err, retry?")
        }
    }

}