package io.github.alstn113.transfer.application.port.out.exception

class WithdrawServerException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)