package io.github.alstn113.transfer.domain

import java.math.BigDecimal

data class Transfer private constructor(
    val id: Long,
    val fromWalletId: Long,
    val toWalletId: Long,
    val amount: BigDecimal,
    val transactionId: String,
    val state: TransferState,
) {

    fun changeState(toState: TransferState): Transfer {
        this.state.transitionTo(toState)
        return this.copy(state = toState)
    }

    companion object {
        fun create(
            fromWalletId: Long,
            toWalletId: Long,
            amount: BigDecimal,
            transactionId: String,
        ): Transfer {
            return Transfer(
                id = 0,
                fromWalletId = fromWalletId,
                toWalletId = toWalletId,
                amount = amount,
                transactionId = transactionId,
                state = TransferState.STARTED
            )
        }

        fun reconstruct(
            id: Long,
            fromWalletId: Long,
            toWalletId: Long,
            amount: BigDecimal,
            transactionId: String,
            state: TransferState,
        ): Transfer {
            return Transfer(
                id = id,
                fromWalletId = fromWalletId,
                toWalletId = toWalletId,
                amount = amount,
                transactionId = transactionId,
                state = state
            )
        }
    }
}