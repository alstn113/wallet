package io.github.alstn113.transfer.application.service.withdraw

import io.github.alstn113.transfer.application.port.out.OutboxPort
import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.application.port.out.WalletPort
import io.github.alstn113.transfer.application.service.withdraw.exception.WithdrawFailedException
import io.github.alstn113.transfer.domain.Transfer
import io.github.alstn113.transfer.domain.TransferState
import org.springframework.stereotype.Component

@Component
class WithdrawReconciler(
    private val walletPort: WalletPort,
    private val transferRepository: TransferRepository,
    private val outboxPort: OutboxPort,
) {

    fun reconcile(transfer: Transfer): Nothing {
        val checkResult = runCatching {
            walletPort.getTransaction(
                walletId = transfer.fromWalletId,
                transactionId = transfer.transactionId,
            )
        }

        checkResult.fold(
            onSuccess = { isWithdrawn ->
                if (isWithdrawn) {
                    processRefundAndFail(transfer)
                } else {
                    processSimpleFail(transfer)
                }
            },
            onFailure = { ex -> processDoubleFault(transfer, ex) }
        )
    }

    private fun processRefundAndFail(transfer: Transfer): Nothing {
        val refundState = updateState(transfer, TransferState.REFUND_NEEDED)

        outboxPort.save()

        updateState(refundState, TransferState.FAILED)
        throw WithdrawFailedException("출금 성공 확인되어 환불 요청 후 실패 처리")
    }

    private fun processSimpleFail(transfer: Transfer): Nothing {
        updateState(transfer, TransferState.FAILED)
        throw WithdrawFailedException("출금 미처리 확인되어 실패 처리")
    }

    private fun processDoubleFault(transfer: Transfer, ex: Throwable): Nothing {
        updateState(transfer, TransferState.WITHDRAW_CHECK_REQUIRED)
        throw WithdrawFailedException("출금 상태 확인 불가 (Double Fault)", ex)
    }

    private fun updateState(transfer: Transfer, state: TransferState): Transfer {
        return transferRepository.save(transfer.changeState(state))
    }
}