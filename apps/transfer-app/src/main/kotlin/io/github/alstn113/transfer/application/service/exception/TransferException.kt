package io.github.alstn113.transfer.application.service.exception

import io.github.alstn113.transfer.domain.exception.BaseException

open class TransferException(
    message: String,
    cause: Throwable? = null,
) : BaseException(message, cause)