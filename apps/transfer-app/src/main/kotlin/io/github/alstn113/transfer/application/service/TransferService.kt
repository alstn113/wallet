package io.github.alstn113.transfer.application.service

import io.github.alstn113.transfer.application.port.`in`.TransferUseCase
import io.github.alstn113.transfer.application.port.`in`.dto.TransferCommandDto
import io.github.alstn113.transfer.application.port.`in`.dto.TransferResultDto
import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.application.port.out.WalletPort
import io.github.alstn113.transfer.application.service.step.deposit.DepositProcessor
import io.github.alstn113.transfer.application.service.step.withdraw.WithdrawProcessor
import io.github.alstn113.transfer.domain.Transfer
import org.springframework.stereotype.Service
import java.util.concurrent.TimeoutException

@Service
class TransferService(
    private val transferRepository: TransferRepository,
    private val walletPort: WalletPort,
    private val withdrawProcessor: WithdrawProcessor,
    private val depositProcessor: DepositProcessor,
) : TransferUseCase {

    override fun invoke(command: TransferCommandDto): TransferResultDto {
        val transfer = initTransfer(command)

        // 출금
        //   출금 성공 시 입금 시도
        //   출금 실패 시 이체 실패
        //   출금 타임아웃/5xx 시
        //     출금 상태 조회
        //       출금 완료 시 출금 복구 메세징
        //       출금 미완료 시 이체 실패
        //       조회 실패 시 지연 처리

        // 입금
        //   입금 성공 시 이체 완료
        //   입금 실패 시 출금 복구 메세징
        //   입금 타임아웃/5xx 시
        //     입금 상태 조회
        //       입금 완료 시 이체 완료
        //       입금 미완료 시 출금 복구 메세징
        //       조회 실패 시 지연 처리

        val afterWithdraw = withdrawProcessor.process(transfer)
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