package io.github.alstn113.wallet.application.port.out

import io.github.alstn113.wallet.domain.WalletTransaction

interface WalletTransactionRepository {

    fun findByWalletIdAndTransactionId(walletId: Long, transactionId: String): WalletTransaction?
}