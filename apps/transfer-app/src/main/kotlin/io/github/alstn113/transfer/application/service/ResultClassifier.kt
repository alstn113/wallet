package io.github.alstn113.transfer.application.service

import org.springframework.stereotype.Component
import java.util.concurrent.TimeoutException

@Component
class ResultClassifier {

    fun classify(ex: Throwable?): ExecutionResult {
        return when {
            ex == null -> ExecutionResult.Success
            ex is TimeoutException -> ExecutionResult.Indeterminate(ex)
            ex.message?.contains("5") == true -> ExecutionResult.Indeterminate(ex)
            else -> ExecutionResult.Failure(ex)
        }
    }
}