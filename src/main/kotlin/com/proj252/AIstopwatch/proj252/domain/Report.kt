package com.proj252.AIstopwatch.proj252.domain

import com.proj252.AIstopwatch.proj252.domain.Report
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.JoinColumn
import java.sql.Timestamp
import java.util.Date

@Entity
public class Report{

    @Id
    @JoinColumn(name = "user")
    private var user:User

    @Id
    var reportDate: Date?

    @Column
    var totalTimes: Int
    @Column
    var goalTime: Int

    public fun getUserId(): Long{
        return userId
    }

}

@Entity
data class ChangeTime(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long,
    @Id
    val reportDate: Date?,
    @Id
    var changeTime: Timestamp
)

@Entity
data class WarnTime(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long,
    @Id
    val reportDate: Date?,
    @Id
    var warnTime: Timestamp
)