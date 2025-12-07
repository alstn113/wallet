package io.github.alstn113.transfer.application.service.step.deposit

import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.application.port.out.WalletPort
import io.github.alstn113.transfer.application.port.out.dto.DepositCommandDto
import io.github.alstn113.transfer.application.service.ResultClassifier
import io.github.alstn113.transfer.application.service.StepResult
import io.github.alstn113.transfer.application.service.step.AbstractStepProcessor
import io.github.alstn113.transfer.domain.Transfer
import io.github.alstn113.transfer.domain.TransferState
import org.springframework.stereotype.Component

@Component
class DepositProcessor(
    private val resultClassifier: ResultClassifier,
    private val walletPort: WalletPort,
    private val transferRepository: TransferRepository
) : AbstractStepProcessor(resultClassifier) {

    override fun execute(transfer: Transfer) {
        val command = DepositCommandDto(
            walletId = transfer.toWalletId,
            transactionId = transfer.transactionId,
            amount = transfer.amount,
        )
        walletPort.deposit(command)
    }

    override fun onSuccess(transfer: Transfer): Ste {
        transfer.changeState(TransferState.)
    }

    override fun onFailure(transfer: Transfer): StepResult {
        TODO("Not yet implemented")
    }

    override fun onIndeterminate(transfer: Transfer): StepResult {
        TODO("Not yet implemented")
    }
}
