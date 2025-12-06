package io.github.alstn113.wallet.adapter.out.persistence.jpa.repository

import io.github.alstn113.wallet.adapter.out.persistence.jpa.entity.WalletTransactionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WalletTransactionJpaRepository : JpaRepository<WalletTransactionEntity, Long> {

    fun findByWalletIdAndTransactionId(walletId: Long, transactionId: String): WalletTransactionEntity?
}