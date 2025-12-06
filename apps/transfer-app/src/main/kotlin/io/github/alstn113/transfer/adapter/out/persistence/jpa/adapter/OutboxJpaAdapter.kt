package io.github.alstn113.transfer.adapter.out.persistence.jpa.adapter

import io.github.alstn113.transfer.application.port.out.OutboxPort
import org.springframework.stereotype.Component

@Component
class OutboxJpaAdapter : OutboxPort {

    override fun save() {
        TODO("Not yet implemented")
    }
}