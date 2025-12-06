package io.github.alstn113.wallet.adapter.out.jpa.adapter

import io.github.alstn113.wallet.adapter.out.jpa.repository.WalletTransactionJpaRepository
import io.github.alstn113.wallet.application.port.out.WalletTransactionRepository
import io.github.alstn113.wallet.domain.WalletTransaction
import org.springframework.stereotype.Component

@Component
class WalletTransactionJpaAdapter(
    private val walletTransactionJpaRepository: WalletTransactionJpaRepository,
) : WalletTransactionRepository {

    override fun findByWalletIdAndTransactionId(walletId: Long, transactionId: String): WalletTransaction? {
        return walletTransactionJpaRepository
            .findByWalletIdAndTransactionId(walletId = walletId, transactionId = transactionId)
            ?.toDomain()
    }
}