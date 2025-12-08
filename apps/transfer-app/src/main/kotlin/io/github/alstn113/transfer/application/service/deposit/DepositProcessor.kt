package io.github.alstn113.transfer.application.service.deposit

import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.application.port.out.WalletPort
import io.github.alstn113.transfer.application.port.out.dto.DepositCommandDto
import io.github.alstn113.transfer.application.service.AbstractStepProcessor
import io.github.alstn113.transfer.application.service.ResultClassifier
import io.github.alstn113.transfer.application.service.deposit.exception.DepositFailedException
import io.github.alstn113.transfer.domain.Transfer
import io.github.alstn113.transfer.domain.TransferState
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class DepositProcessor(
    private val resultClassifier: ResultClassifier,
    private val walletPort: WalletPort,
    private val transferRepository: TransferRepository,
    private val depositReconciler: DepositReconciler,
) : AbstractStepProcessor(resultClassifier) {

    override fun execute(transfer: Transfer) {
        val command = DepositCommandDto(
            walletId = transfer.toWalletId,
            transactionId = transfer.transactionId,
            amount = transfer.amount,
        )
        walletPort.deposit(command)
    }

    @Transactional
    override fun onSuccess(transfer: Transfer): Transfer {
        val nextState = transfer.changeState(TransferState.COMPLETED)
        return transferRepository.save(nextState)
    }

    @Transactional
    override fun onFailure(transfer: Transfer): Nothing {
        val nextState = transfer.changeState(TransferState.DEPOSIT_CHECK_REQUIRED)
        transferRepository.save(nextState)

        throw DepositFailedException("입금 실패")
    }

    override fun onIndeterminate(transfer: Transfer): Transfer {
        depositReconciler.reconcile(transfer)

        throw DepositFailedException("도달하지 않는 예외")
    }
}
