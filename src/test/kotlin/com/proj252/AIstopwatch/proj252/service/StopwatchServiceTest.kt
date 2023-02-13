package com.proj252.AIstopwatch.proj252.service

import com.proj252.AIstopwatch.proj252.repository.SDJStopwatchRepo
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJpaTest
//pass
class StopwatchServiceTest {

    @Mock
    lateinit var stopwatchRepo: SDJStopwatchRepo

    lateinit var stopwatchService: StopwatchService

    @Test
    fun getStopwatch() {
    }

    @Test
    fun runStopwatch() {
    }

    @Test
    fun pauseStopwatch() {
    }

    @Test
    fun warnStopwatch() {
    }

    @Test
    fun saveStopwatch() {
    }
}