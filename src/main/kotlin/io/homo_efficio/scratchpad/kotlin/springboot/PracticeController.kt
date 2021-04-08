package io.homo_efficio.scratchpad.kotlin.springboot

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequiredArgsConstructor
class PracticeController {

    @GetMapping("/basic")
    fun basic(): Flux<Int> {
        return Flux.just(1, 2, 3)
            .log()
            .map { it * 2 }
    }
}
