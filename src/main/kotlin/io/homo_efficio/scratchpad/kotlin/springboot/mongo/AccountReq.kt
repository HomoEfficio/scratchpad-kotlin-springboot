package io.homo_efficio.scratchpad.kotlin.springboot.mongo

data class AccountReq(
    val accountId: String?,
    val accountName: String,
    val accountBalance: Int
) {
    fun toEntity(): Account {
        return Account(accountId, accountName, accountBalance)
    }
}

