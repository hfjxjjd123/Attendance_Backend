package com.proj252.AIstopwatch.proj252.domain

import com.proj252.AIstopwatch.proj252.service.getDate
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.NonNull
import java.util.*

@Entity
data class TmpChangeTimeTrack(
    @Id @NonNull
    private val 


) {

    //자동설정시 time을 초기화할 때 주의
    @Id @NonNull
    private var changeTime: Date = Date(System.currentTimeMillis())
    @Column @NonNull
    private var reportDate: String = getDate().format(Date(System.currentTimeMillis()))
}