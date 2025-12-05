package io.github.alstn113.wallet.adapter.out.jpa.adapter

import io.github.alstn113.wallet.application.port.out.WalletRepository
import io.github.alstn113.wallet.domain.Wallet
import io.github.alstn113.wallet.domain.WalletTransaction
import org.springframework.stereotype.Component

@Component
class WalletJpaAdapter : WalletRepository {

    override fun findByIdWithLock(id: Long): Wallet? {
        TODO("Not yet implemented")
    }

    override fun saveWithTransaction(
        wallet: Wallet,
        transaction: WalletTransaction,
    ): WalletTransaction {
        TODO("Not yet implemented")
    }
}