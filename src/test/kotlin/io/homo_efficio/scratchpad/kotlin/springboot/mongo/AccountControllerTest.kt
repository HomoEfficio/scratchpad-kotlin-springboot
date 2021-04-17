package io.homo_efficio.scratchpad.kotlin.springboot.mongo

import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.body
import reactor.core.publisher.Mono

@WebFluxTest(AccountController::class)
class AccountControllerTest {

    @MockBean private lateinit var accountService: AccountService
    @Autowired private lateinit var wtc: WebTestClient


    @Test
    fun `account save`() {
        `when`(accountService.save(any()))
            .thenReturn(AccountVM("1", "omwomw", 3000))

        wtc.post().uri("/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(AccountReq(null, "omwomw", 3000)))
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .consumeWith { println(it) }
            .jsonPath("$.accountId").isNotEmpty
            .jsonPath("$.accountName").isEqualTo("omwomw")
            .jsonPath("$.accountBalance").isEqualTo(3000)
    }
}
