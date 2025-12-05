package io.github.alstn113.transfer.adapter.out.jpa.entity

import jakarta.persistence.*

@Entity
class OutboxEntity(
    @Column(nullable = false, unique = true)
    val eventId: String,

    @Column(nullable = false)
    val aggregateType: String,

    @Column(nullable = false)
    val aggregateId: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val eventType: OutboxEventType,

    @Column(nullable = false, columnDefinition = "TEXT")
    val payload: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: OutboxStatus = OutboxStatus.PENDING,
) : AuditableEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    enum class OutboxEventType {
        WITHDRAWAL_ROLLBACK_REQUESTED,
        DEPOSIT_ROLLBACK_REQUESTED,
    }

    enum class OutboxStatus {
        PENDING,
        COMPLETED,
        FAILED
    }
}