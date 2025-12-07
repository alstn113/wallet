package io.github.alstn113.transfer.application.service.step

import io.github.alstn113.transfer.application.service.ExecutionResult
import io.github.alstn113.transfer.application.service.ResultClassifier
import io.github.alstn113.transfer.domain.Transfer

abstract class AbstractStepProcessor(
    private val resultClassifier: ResultClassifier
) {

    fun process(transfer: Transfer): Transfer {
        val ex = runCatching { execute(transfer) }.exceptionOrNull()
        val result = resultClassifier.classify(ex)

        return when (result) {
            is ExecutionResult.Success -> onSuccess(transfer)
            is ExecutionResult.Failure -> onFailure(transfer)
            is ExecutionResult.Indeterminate -> onIndeterminate(transfer)
        }
    }

    protected abstract fun execute(transfer: Transfer)
    protected abstract fun onSuccess(transfer: Transfer): Transfer
    protected abstract fun onFailure(transfer: Transfer): Nothing
    protected abstract fun onIndeterminate(transfer: Transfer): Transfer
}