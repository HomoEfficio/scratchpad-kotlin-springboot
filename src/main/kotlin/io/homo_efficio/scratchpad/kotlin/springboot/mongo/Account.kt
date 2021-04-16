package io.homo_efficio.scratchpad.kotlin.springboot.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Account(
    @Id
    val id: String?,
    val name: String,
    val balance: Int
)
