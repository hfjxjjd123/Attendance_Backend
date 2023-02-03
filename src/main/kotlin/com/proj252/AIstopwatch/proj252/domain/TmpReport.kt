package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.Data
import lombok.Getter
import lombok.NonNull
import lombok.RequiredArgsConstructor
import lombok.Setter
import java.sql.Time
import java.util.Date

@Entity
@Data //id setter는 막아야
class TmpReport {
//Timed이 아니라 Int 타입 사용

    @Id @NonNull
    private lateinit var date: Date
    @Column
    private var totalTime: Int=0
    @Column
    private var goalTime: Int? = null
    @Column
    private var goalBeginning: Date? = null
    @Column
    private var goalEnding: Date? = null
    @Column @NonNull
    private var userId: Long = -1


}