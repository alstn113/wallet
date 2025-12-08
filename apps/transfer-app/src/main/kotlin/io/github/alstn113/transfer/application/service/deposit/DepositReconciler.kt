package io.github.alstn113.transfer.application.service.deposit

import io.github.alstn113.transfer.application.port.out.OutboxPort
import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.application.port.out.WalletPort
import io.github.alstn113.transfer.application.service.deposit.exception.DepositFailedException
import io.github.alstn113.transfer.domain.Transfer
import io.github.alstn113.transfer.domain.TransferState
import org.springframework.stereotype.Component

@Component
class DepositReconciler(
    private val transferRepository: TransferRepository,
    private val outboxPort: OutboxPort,
    private val walletPort: WalletPort,
) {

    fun reconcile(transfer: Transfer): Transfer {
        val checkResult = runCatching {
            walletPort.getTransaction(
                walletId = transfer.toWalletId,
                transactionId = transfer.transactionId,
            )
        }

        checkResult.fold(
            onSuccess = { isDeposited ->
                if (isDeposited) {
                    return processSuccess(transfer)
                } else {
                    compensateAndFail(transfer, "입금 미처리 확인되어 환불")
                }
            },
            onFailure = { ex -> processDoubleFault(transfer, ex) }
        )
    }

    fun compensateAndFail(transfer: Transfer, reason: String): Nothing {
        val refundState = updateState(transfer, TransferState.REFUND_NEEDED)
        outboxPort.save()
        updateState(refundState, TransferState.FAILED)

        throw DepositFailedException(reason)
    }

    private fun processSuccess(transfer: Transfer): Transfer {
        val completed = transfer.changeState(TransferState.COMPLETED)
        return transferRepository.save(completed)
    }

    private fun processDoubleFault(transfer: Transfer, ex: Throwable) {
        updateState(transfer, TransferState.DEPOSIT_CHECK_REQUIRED)

        throw DepositFailedException("입금 상태 확인 불가 (Double Fault)", ex)
    }

    private fun updateState(transfer: Transfer, state: TransferState): Transfer {
        val updatedTransfer = transfer.changeState(state)
        return transferRepository.save(updatedTransfer)
    }
}
