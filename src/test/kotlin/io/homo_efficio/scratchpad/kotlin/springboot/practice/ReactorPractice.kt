package io.homo_efficio.scratchpad.kotlin.springboot.practice

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux

class ReactorPractice {

    @DisplayName("구독을 두 번 하면 시퀀스도 두 번 생성된다.")
    @Test
    fun subscribe_twice() {
        val seq = Flux.just(1, 2, 3)
            .doOnNext { println(it) }

        seq.subscribe { println("subscription 1: $it") }
        seq.subscribe { println("subscription 2: ${it * 2}") }
    }
}
