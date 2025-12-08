package io.github.alstn113.transfer.application.port.out.exception

import io.github.alstn113.transfer.domain.exception.BaseException

class WithdrawTimeoutException(
    message: String,
    cause: Throwable? = null,
) : BaseException(message, cause)