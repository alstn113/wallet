package io.github.alstn113.transfer.adapter.out.persistence.jpa.adapter

import io.github.alstn113.transfer.adapter.out.persistence.jpa.entity.TransferEntity
import io.github.alstn113.transfer.adapter.out.persistence.jpa.entity.TransferLogEntity
import io.github.alstn113.transfer.domain.Transfer

object TransferMapper {

    fun mapToTransferEntity(transfer: Transfer): TransferEntity {
        return TransferEntity(
            fromWalletId = transfer.fromWalletId,
            toWalletId = transfer.toWalletId,
            amount = transfer.amount,
            transactionId = transfer.transactionId
        )
    }

    fun mapToTransferLogEntity(transfer: Transfer): TransferLogEntity {
        return TransferLogEntity(
            transactionId = transfer.transactionId,
            state = transfer.state
        )
    }

    fun mapToTransfer(
        transferEntity: TransferEntity,
        transferLogEntity: TransferLogEntity,
    ): Transfer {
        return Transfer.reconstruct(
            id = transferEntity.id,
            fromWalletId = transferEntity.fromWalletId,
            toWalletId = transferEntity.toWalletId,
            amount = transferEntity.amount,
            transactionId = transferEntity.transactionId,
            state = transferLogEntity.state
        )
    }
}