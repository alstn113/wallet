package io.github.alstn113.transfer.adapter.out.messaging.kafka

import io.github.alstn113.transfer.application.port.out.SendTransferCompensationPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class TransferCompensationKafkaAdapter(
    private val kafkaTemplate: KafkaTemplate<String, String>
): SendTransferCompensationPort {

    override fun send() {
        // TODO: 실제 보상 메시지로 변경 필요
        kafkaTemplate.send("transfer-compensation-topic", "compensation-message")
    }
}