package io.github.alstn113.wallet.adapter.out.persistence.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditableEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: Instant = Instant.MIN

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: Instant = Instant.MIN
}