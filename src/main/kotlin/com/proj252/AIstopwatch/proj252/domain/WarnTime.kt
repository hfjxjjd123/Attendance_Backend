package com.proj252.AIstopwatch.proj252.domain

import com.proj252.AIstopwatch.proj252.service.getDate
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.Data
import lombok.NonNull
import java.text.SimpleDateFormat
import java.util.*

@Entity
@Data
class WarnTime {

    @Id @NonNull
    private var warnTime: Date = Date(System.currentTimeMillis())
    @Column @NonNull
    private var reportDate: String = getDate().format(Date(System.currentTimeMillis()))
}