package io.github.alstn113.transfer.adapter.out.persistence.jpa.repository

import io.github.alstn113.transfer.adapter.out.persistence.jpa.entity.TransferEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TransferJpaRepository : JpaRepository<TransferEntity, Long> {
}