package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.NonNull
import lombok.RequiredArgsConstructor
import lombok.Setter
import java.sql.Time
import java.util.Date

@Entity
data class TmpReport(
//Timed이 아니라 Int 타입 사용

    @Id @NonNull
    val date: Date,
    @OneToOne @JoinColumn(name = "userId")
    val user: User,

    @Column
    var totalTime: Int,
    @Column
    var goalTime: Int?,
    @Column
    var goalBeginning: Date?,
    @Column
    var goalEnding: Date?,

    @OneToMany(mappedBy = "tmpReport")
    val changeTimes: List<ChangeTime>,
    @OneToMany(mappedBy = "tmpReport")
    val warnTimes: List<WarnTime>
)