package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.NonNull
import lombok.RequiredArgsConstructor
import lombok.Setter

@Entity
@RequiredArgsConstructor
class Alarm {

    @Id
    @Getter @Setter
    private var melody: String = "default"

    @Column
    @Getter @Setter
    private var ison: Boolean = false

    @Column @NonNull
    @Getter
    private var userId: Long = -1
    //userId == -1일 때 초기화 오류


}