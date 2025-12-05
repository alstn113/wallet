package io.github.alstn113.transfer.application.service

import io.github.alstn113.transfer.application.port.`in`.TransferUseCase
import io.github.alstn113.transfer.application.port.`in`.dto.TransferCommandDto
import io.github.alstn113.transfer.application.port.`in`.dto.TransferResultDto
import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.domain.Transfer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class TransferService(
    private val transferRepository: TransferRepository,
    private val withdrawalStep: WithdrawalStep,
    private val depositStep: DepositStep,
) : TransferUseCase {

    @Transactional
    override fun invoke(command: TransferCommandDto): TransferResultDto {
        val transfer = initTransfer(command)

        val stepResult = withdrawalStep.process(transfer)
        val withdrawnTransfer = when (stepResult) {
            is StepResult.Next -> stepResult.transfer
            is StepResult.Stop -> return stepResult.result
        }

        return depositStep.process(withdrawnTransfer)
    }

    private fun initTransfer(command: TransferCommandDto): Transfer {
        val transfer = Transfer.create(
            fromWalletId = command.fromWalletId,
            toWalletId = command.toWalletId,
            amount = command.amount,
            transactionId = command.transactionId
        )
        return transferRepository.save(transfer)
    }
}
