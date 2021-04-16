package io.homo_efficio.scratchpad.kotlin.springboot.mongo

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RequestMapping("/accounts")
@RestController
class AccountController(
    private val accountService: AccountService
) {

    private val log = LoggerFactory.getLogger(javaClass)


    @PostMapping
    fun `account 저장`(@RequestBody account: Account): Mono<ResponseEntity<AccountVM>> {
        return accountService.save(account)
            .doOnNext { log.debug("saved accountVM: {}", it) }
            .map { ResponseEntity.ok(it) }
    }

    @GetMapping
    fun `account 모두 조회`(): Mono<ResponseEntity<List<AccountVM>>> {
        return accountService.findAll()
            .collectList()
            .map { ResponseEntity.ok(it) }
    }
}
