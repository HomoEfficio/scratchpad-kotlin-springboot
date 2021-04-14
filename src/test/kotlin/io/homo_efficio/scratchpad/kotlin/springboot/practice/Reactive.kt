package io.homo_efficio.scratchpad.kotlin.springboot.practice

import reactor.core.publisher.Flux

fun main() {
    val log = MyLogger.log

    log.debug("Start")

    val flux1 = Flux.just(1, 2, 3)

    flux1.subscribe { log.debug("v: {}", it) }
}
