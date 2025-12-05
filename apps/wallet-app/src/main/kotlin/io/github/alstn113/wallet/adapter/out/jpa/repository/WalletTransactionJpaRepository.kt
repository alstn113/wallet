package io.github.alstn113.wallet.adapter.out.jpa.repository

import io.github.alstn113.wallet.adapter.out.jpa.entity.WalletTransactionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WalletTransactionJpaRepository : JpaRepository<WalletTransactionEntity, Long>