package io.github.alstn113.transfer.application.port.out.exception

class DepositServerException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)