package io.github.alstn113.wallet.adapter.`in`.kafka

import io.github.alstn113.wallet.application.port.`in`.CompensateWalletUseCase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class WalletCompensationKafkaAdapter(
    private val compensateWalletUseCase: CompensateWalletUseCase,
) {

    // TODO: 메시지 타입 실제 보상 커맨드로 변경 필요
    @KafkaListener(topics = ["transfer-compensation-topic"], groupId = "wallet-group")
    fun listen() {
        compensateWalletUseCase.compensate()
    }
}