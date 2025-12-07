package io.github.alstn113.transfer.application.service.step.withdraw

import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.application.port.out.WalletPort
import io.github.alstn113.transfer.application.port.out.dto.WithdrawCommandDto
import io.github.alstn113.transfer.application.service.ResultClassifier
import io.github.alstn113.transfer.application.service.step.AbstractStepProcessor
import io.github.alstn113.transfer.domain.Transfer
import io.github.alstn113.transfer.domain.TransferState
import org.springframework.stereotype.Component

@Component
class WithdrawProcessor(
    private val resultClassifier: ResultClassifier,
    private val walletPort: WalletPort,
    private val transferRepository: TransferRepository,
) : AbstractStepProcessor(resultClassifier) {

    override fun execute(transfer: Transfer) {
        val command = WithdrawCommandDto(
            walletId = transfer.fromWalletId,
            transactionId = transfer.transactionId,
            amount = transfer.amount,
        )
        walletPort.withdraw(command)
    }

    override fun onSuccess(transfer: Transfer): Transfer {
        val updatedTransfer = transfer.changeState(TransferState.WITHDRAW_SUCCESS)
        return transferRepository.save(updatedTransfer)
    }

    override fun onFailure(transfer: Transfer): Nothing {
        val updatedTransfer = transfer.changeState(TransferState.FAILED)
        transferRepository.save(updatedTransfer)

        throw IllegalStateException("출금 실패 처리")
    }

    override fun onIndeterminate(transfer: Transfer): Transfer {
        val withdrawn = try {
            walletPort.getTransaction() // TODO: 여기서 출급, 입금 상태 조회
        } catch (_: Exception) {
            val updatedTransfer = transfer.changeState(TransferState.)
        }
    }
}