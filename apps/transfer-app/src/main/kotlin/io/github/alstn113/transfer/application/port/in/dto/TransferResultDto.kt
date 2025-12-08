package io.github.alstn113.transfer.application.port.`in`.dto

import io.github.alstn113.transfer.domain.Transfer

data class TransferResultDto(
    val id: Long,
) {

    companion object {
        fun from(transfer: Transfer): TransferResultDto {
            return TransferResultDto(
                id = transfer.id,
            )
        }
    }
}
