package io.github.alstn113.transfer.application.service.withdraw

import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.application.port.out.WalletPort
import io.github.alstn113.transfer.application.port.out.dto.WithdrawCommandDto
import io.github.alstn113.transfer.application.service.AbstractStepProcessor
import io.github.alstn113.transfer.application.service.ResultClassifier
import io.github.alstn113.transfer.application.service.withdraw.exception.WithdrawFailedException
import io.github.alstn113.transfer.domain.Transfer
import io.github.alstn113.transfer.domain.TransferState
import org.springframework.stereotype.Component

@Component
class WithdrawProcessor(
    private val resultClassifier: ResultClassifier,
    private val walletPort: WalletPort,
    private val transferRepository: TransferRepository,
    private val withdrawCheckProcessor: WithdrawCheckProcessor,
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

        throw WithdrawFailedException("출금 실패")
    }

    override fun onIndeterminate(transfer: Transfer): Transfer {

    }
}