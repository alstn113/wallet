package io.github.alstn113.wallet.adapter.out.persistence.jpa.entity

import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.*

@Entity
class WalletEntity(
    @Column(nullable = false)
    val balance: Long,
) : AuditableEntity() {

    @Id
    @Tsid
    val id: Long = 0
}