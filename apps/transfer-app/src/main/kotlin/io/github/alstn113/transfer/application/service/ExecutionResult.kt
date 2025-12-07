package io.github.alstn113.transfer.application.service

sealed class ExecutionResult {
    data object Success : ExecutionResult()
    data class Failure(val cause: Throwable?) : ExecutionResult()
    data class Indeterminate(val cause: Throwable?) : ExecutionResult()
}