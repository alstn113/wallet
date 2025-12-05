package io.github.alstn113.wallet.adapter.out.jpa.adapter

import io.github.alstn113.wallet.application.port.out.WalletTransactionRepository
import io.github.alstn113.wallet.domain.WalletTransaction
import org.springframework.stereotype.Component

@Component
class WalletTransactionJpaAdapter : WalletTransactionRepository {

    override fun findByWalletIdAndTransactionId(
        walletId: Long,
        transactionId: String,
    ): WalletTransaction {
        TODO("Not yet implemented")
    }
}