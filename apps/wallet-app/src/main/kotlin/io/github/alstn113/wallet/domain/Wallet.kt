package io.github.alstn113.wallet.domain

import java.math.BigDecimal

data class Wallet private constructor(
    val id: Long,
    val balance: BigDecimal,
) {

    fun withdraw(amount: BigDecimal): Wallet {
        if (this.balance < amount) {
            throw IllegalArgumentException("잔액이 부족합니다.")
        }

        val newBalance = this.balance.subtract(amount)
        return this.copy(balance = newBalance)
    }

    fun deposit(amount: BigDecimal): Wallet {
        val newBalance = this.balance.add(amount)
        return this.copy(balance = newBalance)
    }

    companion object {
        fun create(balance: BigDecimal): Wallet {
            return Wallet(id = 0, balance = balance)
        }

        fun reconstruct(id: Long, balance: BigDecimal): Wallet {
            return Wallet(id = id, balance = balance)
        }
    }
}