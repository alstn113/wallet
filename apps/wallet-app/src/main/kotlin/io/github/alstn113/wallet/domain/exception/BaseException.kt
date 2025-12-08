package io.github.alstn113.wallet.domain.exception

abstract class BaseException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)