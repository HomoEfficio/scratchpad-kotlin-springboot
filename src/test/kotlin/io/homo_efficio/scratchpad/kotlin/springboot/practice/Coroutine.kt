package io.homo_efficio.scratchpad.kotlin.springboot.practice

import io.homo_efficio.scratchpad.kotlin.springboot.lazyLogger
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread


fun main() {
    val log = MyLogger.log

    log.debug("Start")

    GlobalScope.launch {
        delay(1000)
        log.debug("Hello")
    }

    Thread.sleep(2000)
    log.debug("Stop")

    log.debug("------------------------")
    val aLong = AtomicLong()

    val start1 = System.currentTimeMillis()
    log.debug("START: {}", start1)
    for (i in 1..100_000L) {
        thread(start = true) {
            val value = aLong.getAndAdd(1)
            if (value % 10_000 == 0L) {
                log.debug("aLong: {}", value)
            }
        }
    }
    val end1 = System.currentTimeMillis()
    log.debug("END  : {}", end1)
    log.debug("소요 시간: {}, 값: {}", (end1 - start1), aLong)

    log.debug("------------------------")
    val aLong2 = AtomicLong()

    val start2 = System.currentTimeMillis()
    log.debug("START: {}", start2)
    for (i in 1..100_000L) {
        GlobalScope.launch {
            val value = aLong2.getAndAdd(1)
            if (value % 10_000 == 0L) {
                log.debug("aLong2: {}", value)
            }
        }
    }
    val end2 = System.currentTimeMillis()
    log.debug("END  : {}", end2)
    log.debug("소요 시간: {}, 값: {}", (end2 - start2), aLong2)
}

class MyLogger {

    companion object {
        val log by lazyLogger()
    }
}
