package io.github.alstn113.wallet.adapter.out.persistence.jpa.entity

import jakarta.persistence.*

@Entity
class WalletEntity(
    @Column(nullable = false)
    val balance: Long,
) : AuditableEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}