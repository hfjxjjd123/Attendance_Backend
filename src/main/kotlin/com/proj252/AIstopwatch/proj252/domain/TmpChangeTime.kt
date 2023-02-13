package com.proj252.AIstopwatch.proj252.domain

import com.proj252.AIstopwatch.proj252.service.getDate
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import lombok.NonNull
import java.util.*

@Entity
data class TmpChangeTime(
    @Id @NonNull
    private val track: Date,
    @ManyToOne @JoinColumn(name = "tmpReportDate")
    private val tmpReport: TmpReport

)