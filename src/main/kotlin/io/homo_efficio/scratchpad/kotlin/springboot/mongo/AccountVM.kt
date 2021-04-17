package io.homo_efficio.scratchpad.kotlin.springboot.mongo

data class AccountVM(
    val accountId: String,
    val accountName: String,
    val accountBalance: Int
) {
    companion object {
        fun fromEntity(account: Account): AccountVM {
            return AccountVM(account.id!!, account.name, account.balance)
        }
    }
}

