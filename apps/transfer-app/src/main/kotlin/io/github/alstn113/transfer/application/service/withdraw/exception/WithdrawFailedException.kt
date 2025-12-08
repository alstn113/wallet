package io.github.alstn113.transfer.application.service.withdraw.exception

import io.github.alstn113.transfer.application.service.exception.TransferException

class WithdrawFailedException(
    message: String,
    cause: Throwable? = null,
) : TransferException(message, cause)