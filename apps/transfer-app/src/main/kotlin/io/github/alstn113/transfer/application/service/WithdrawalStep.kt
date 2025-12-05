package io.github.alstn113.transfer.application.service

import io.github.alstn113.transfer.application.port.`in`.dto.TransferResultDto
import io.github.alstn113.transfer.application.port.out.OutboxPort
import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.application.port.out.WalletPort
import io.github.alstn113.transfer.domain.Transfer
import io.github.alstn113.transfer.domain.TransferState
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeoutException

@Component
class WithdrawalStep(
    private val walletPort: WalletPort,
    private val transferRepository: TransferRepository,
    private val outboxPort: OutboxPort,
) {

    @Transactional
    fun process(transfer: Transfer): StepResult {
        return runCatching {
            walletPort.withdraw()

            val updatedTransfer = transfer.changeState(TransferState.WITHDRAW_SUCCESS)
            val savedTransfer = transferRepository.save(updatedTransfer)

            StepResult.Next(savedTransfer)
        }.recoverCatching { e ->
            handleError(transfer, e)
        }.getOrElse {
            (it as? StepResult.Stop) ?: StepResult.Stop(TransferResultDto(id = 0L))
        }
    }

    private fun handleError(transfer: Transfer, e: Throwable): StepResult {
        if (!isIndeterminate(e)) {
            val updatedTransfer = transfer.changeState(TransferState.FAILED)
            val savedTransfer = transferRepository.save(updatedTransfer)

            return StepResult.Stop(TransferResultDto(id = 0L))
        }

        val isSucceeded = try {
            walletPort.checkWithdrawal()
        } catch (ex: Exception) {
            // 조회 실패 시 처리 필요
            return StepResult.Stop(TransferResultDto(id = 0L))
        }

        if (isSucceeded) {
            val updatedTransfer = transfer.changeState(TransferState.REFUND_NEEDED)
            val savedTransfer = transferRepository.save(updatedTransfer)
            // 아웃박스 저장 및 이벤트 발행

            return StepResult.Stop(TransferResultDto(id = 0L))
        } else {
            val updatedTransfer = transfer.changeState(TransferState.FAILED)
            val savedTransfer = transferRepository.save(updatedTransfer)

            return StepResult.Stop(TransferResultDto(id = 0L))
        }
    }

    private fun isIndeterminate(e: Throwable): Boolean {
        return e is TimeoutException || (e is RuntimeException && e.message?.contains("500") == true)
    }
}