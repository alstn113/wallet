package io.github.alstn113.wallet.application.port.out

import io.github.alstn113.wallet.domain.Wallet
import io.github.alstn113.wallet.domain.WalletTransaction

interface WalletRepository {

    fun findByIdWithLock(id: Long): Wallet?

    fun saveWithTransaction(wallet: Wallet, transaction: WalletTransaction): WalletTransaction
}