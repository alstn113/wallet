package io.github.alstn113.wallet.adapter.out.persistence.jpa.repository

import io.github.alstn113.wallet.adapter.out.persistence.jpa.entity.WalletEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WalletJpaRepository : JpaRepository<WalletEntity, Long>