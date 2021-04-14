package io.homo_efficio.scratchpad.kotlin.springboot

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PersonRepository: ReactiveMongoRepository<Person, String> {
}
