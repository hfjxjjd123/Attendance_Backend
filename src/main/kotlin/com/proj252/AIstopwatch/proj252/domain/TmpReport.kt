package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.sql.Time
import java.util.Date

@Entity
class TmpReport {

    @Id
    private var date: Date
    @Column
    private var totalTime: Time
    @Column
    private var goalTime: Time?
    @Column
    private var goalBeginning: Date?
    @Column
    private var goalEnding: Date?
    @Column
    private var userId: Long

    constructor(date: Date, totalTime: Time, goalTime: Time?, goalBeginning: Date?, goalEnding: Date?, userId: Long) {
        this.date = date
        this.totalTime = totalTime
        this.goalTime = goalTime
        this.goalBeginning = goalBeginning
        this.goalEnding = goalEnding
        this.userId = userId
    }


    fun getUserId(): Long{
        return this.userId
    }
    fun getDate(): Date{
        return this.date
    }

    fun setTotalTime(time: Time){
        this.totalTime = time
    }
    fun getTotalTime(): Time{
        return this.totalTime
    }
    fun setGoalTime(time: Time?){
        this.goalTime = time
    }
    fun getGoalTime(): Time?{
        return this.goalTime
    }
    fun setGoalBeginning(datetime: Date?){
        this.goalBeginning = datetime
    }
    fun getGoalBeginning(): Date?{
        return this.goalBeginning
    }
    fun setGoalEnding(datetime:Date?){
        this.goalEnding = datetime
    }
    fun getGoalEnding(): Date?{
        return this.goalEnding
    }



}