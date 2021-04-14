package io.homo_efficio.scratchpad.kotlin.springboot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class InitRunner(
    private val template: ReactiveMongoTemplate,
    private val repository: PersonRepository): CommandLineRunner {

    override fun run(vararg args: String?) {
        template.dropCollection(Person::class.java)
            .subscribe {
                // drop 명령은 실행되는데 아래 메시지 출력은 안 됨
                println(Thread.currentThread().name + ": " + "DROP Collection 완료")
            }


        template.insert(
            Person(
                "id-001",
                "hanmomhanda",
                Random.nextInt()
            )
        ).block()!!


        println(Thread.currentThread().name + ": " + "INSERT 완료")

        val mono1 = repository.findById("id-001")


        repository.findById("id-001")
            .subscribe {
                println(Thread.currentThread().name + ": " + "SELECT 완료")
                println(Thread.currentThread().name + ": " + it.name)
            }

        template.save(
            Person(
                "id-001",
                "RENAMED hanmomhanda",
                Random.nextInt()
            )
        ).block()!!

        repository.findById("id-001")
            .subscribe {
                println(Thread.currentThread().name + ": " + "SELECT 완료")
                println(Thread.currentThread().name + ": " + it.name)
            }

        mono1.subscribe {
            println(Thread.currentThread().name + ": " + "mono1 SELECT 완료")
            println(Thread.currentThread().name + ": " + it.name)
        }

        println(Thread.currentThread().name + ": " + "METHOD 종료")
    }

    private fun generateDocument(): Person {
        return template.insert(
            Person(
                "id-001",
                "hanmomhanda",
                Random.nextInt()
            )
        ).block()!!
    }
}
