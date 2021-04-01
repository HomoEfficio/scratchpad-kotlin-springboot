package io.homo_efficio.scratchpad.kotlin.springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScratchpadKotlinSpringbootApplication

fun main(args: Array<String>) {
    runApplication<ScratchpadKotlinSpringbootApplication>(*args)
}
