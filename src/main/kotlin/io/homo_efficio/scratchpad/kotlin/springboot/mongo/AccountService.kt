package io.homo_efficio.scratchpad.kotlin.springboot.mongo

import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AccountService(
    private val mongoTemplate: ReactiveMongoTemplate
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun save(account: Account): Mono<AccountVM> {
        val dbAccountVM: Mono<AccountVM> = mongoTemplate.save(account)
            .map { AccountVM.fromEntity(it) }
        log.debug("accountVM: {}", dbAccountVM)
        return dbAccountVM
    }

    fun findAll(): Flux<AccountVM> {
        return mongoTemplate.findAll(Account::class.java)
            .map { AccountVM.fromEntity(it) }
    }
}
