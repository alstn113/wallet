package io.github.alstn113.transfer.application.port.out

import io.github.alstn113.transfer.domain.Transfer


interface TransferRepository {

    fun save(transfer: Transfer): Transfer
}