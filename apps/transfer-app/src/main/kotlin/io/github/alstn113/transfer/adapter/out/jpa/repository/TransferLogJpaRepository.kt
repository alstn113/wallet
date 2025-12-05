package io.github.alstn113.transfer.adapter.out.jpa.repository

import io.github.alstn113.transfer.adapter.out.jpa.entity.TransferLogEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TransferLogJpaRepository : JpaRepository<TransferLogEntity, Long> {

    fun findByTransactionId(transactionId: String): TransferLogEntity?
}