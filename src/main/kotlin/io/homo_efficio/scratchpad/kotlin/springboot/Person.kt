package io.homo_efficio.scratchpad.kotlin.springboot

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Person(
    @Id
    val id: String?,
    val name: String,
    val age: Int
)
