package io.github.alstn113.transfer.application.service

import io.github.alstn113.transfer.application.port.`in`.TransferUseCase
import io.github.alstn113.transfer.application.port.`in`.dto.TransferCommandDto
import io.github.alstn113.transfer.application.port.`in`.dto.TransferResultDto
import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.application.service.deposit.DepositProcessor
import io.github.alstn113.transfer.application.service.exception.TransferException
import io.github.alstn113.transfer.application.service.withdraw.WithdrawProcessor
import io.github.alstn113.transfer.domain.Transfer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransferService(
    private val transferRepository: TransferRepository,
    private val withdrawProcessor: WithdrawProcessor,
    private val depositProcessor: DepositProcessor,
) : TransferUseCase {

    @Transactional(noRollbackFor = [TransferException::class])
    override fun invoke(command: TransferCommandDto): TransferResultDto {
        val transfer = initTransfer(command)

        // 출금 성공 시 다음 단계인 입금 처리 진행
        // 출금 실패 시 이체 실패 / throw
        // 출금 불분명(타임아웃/5xx)
        //   출금 상태 조회
        //     출금 완료 시 / 출금 복구 메세징 / throw
        //     출금 미완료 시 이체 실패 / throw
        //     출금 조회 실패 시 / 지연 처리 / throw
        val afterWithdraw = withdrawProcessor.process(transfer)

        // 입금 성공 시 이체 완료
        // 입금 실패 시 출금 복구 메세징
        // 입금 불분명(타임아웃/5xx)
        //   입금 상태 조회
        //     입금 완료 시 이체 완료
        //     입금 미완료 시 / 출금 복구 메세징 / throw
        //     입금 조회 실패 시 / 지연 처리 / throw
        val afterDeposit = depositProcessor.process(afterWithdraw)

        return TransferResultDto.from(afterDeposit)
    }

    private fun initTransfer(command: TransferCommandDto): Transfer {
        val transfer = Transfer.create(
            fromWalletId = command.fromWalletId,
            toWalletId = command.toWalletId,
            amount = command.amount,
            transactionId = command.transactionId,
        )
        return transferRepository.save(transfer)
    }
}