package io.github.alstn113.transfer.application.service.deposit

import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.application.port.out.WalletPort
import io.github.alstn113.transfer.application.port.out.dto.DepositCommandDto
import io.github.alstn113.transfer.application.service.AbstractStepProcessor
import io.github.alstn113.transfer.application.service.ResultClassifier
import io.github.alstn113.transfer.domain.Transfer
import io.github.alstn113.transfer.domain.TransferState
import org.springframework.stereotype.Component

@Component
class DepositProcessor(
    private val resultClassifier: ResultClassifier,
    private val walletPort: WalletPort,
    private val transferRepository: TransferRepository,
    private val depositCheckProcessor: DepositCheckProcessor,
) : AbstractStepProcessor(resultClassifier) {

    override fun execute(transfer: Transfer) {
        val command = DepositCommandDto(
            walletId = transfer.toWalletId,
            transactionId = transfer.transactionId,
            amount = transfer.amount,
        )
        walletPort.deposit(command)
    }

    override fun onSuccess(transfer: Transfer): Transfer {
        val updatedTransfer = transfer.changeState(TransferState.COMPLETED)
        return transferRepository.save(updatedTransfer)
    }

    override fun onFailure(transfer: Transfer): Nothing {
        val updatedTransfer = transfer.changeState(TransferState.DEPOSIT_CHECK_REQUIRED)
        transferRepository.save(updatedTransfer)

        throw IllegalStateException("입금 실패 처리")
    }

    override fun onIndeterminate(transfer: Transfer): Transfer {

    }
}
