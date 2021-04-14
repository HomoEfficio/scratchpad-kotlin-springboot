package io.homo_efficio.scratchpad.kotlin.springboot.practice

import io.homo_efficio.scratchpad.kotlin.springboot.Person
import io.homo_efficio.scratchpad.kotlin.springboot.PersonRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import reactor.test.StepVerifier
import kotlin.random.Random.Default.nextInt

@DataMongoTest
class PersonRepositoryTest(
    @Autowired private val template: ReactiveMongoTemplate,
    @Autowired private val repository: PersonRepository
) {

    @BeforeEach
    fun beforeEach() {
        template.dropCollection(Person::class.java).subscribe()
    }

    private fun generateDocument(): Person {
        return template.insert(
            Person(
                "id-001",
                "hanmomhanda",
                nextInt()
            )
        ).block()!!
    }

    @Test
    fun `reactive subscribe test`() {
        generateDocument()


        println(Thread.currentThread().name + ": " + "INSERT 완료")

        repository.findById("id-001")
            .`as` { StepVerifier.create(it) }
            .assertNext {
                println(Thread.currentThread().name + ": " + it.name)
                println(Thread.currentThread().name + ": " + "SELECT 완료")
                assertThat(it.id).isEqualTo("id-001")
            }
            .verifyComplete()

        println(Thread.currentThread().name + ": " + "METHOD")
    }
}
