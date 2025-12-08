package io.github.alstn113.transfer.application.service.deposit.exception

import io.github.alstn113.transfer.application.service.exception.TransferException

class DepositFailedException(
    message: String,
    cause: Throwable? = null,
) : TransferException(message, cause)