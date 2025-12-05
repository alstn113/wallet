package io.github.alstn113.transfer.adapter.`in`.web

import io.github.alstn113.transfer.adapter.`in`.web.dto.TransferRequestDto
import io.github.alstn113.transfer.application.port.`in`.TransferUseCase
import io.github.alstn113.transfer.application.port.`in`.dto.TransferResultDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TransferController(
    private val transferUseCase: TransferUseCase,
) {

    @PostMapping("/v1/wallets/transfer")
    fun transfer(
        @RequestBody request: TransferRequestDto,
    ): ResponseEntity<TransferResultDto> {
        val command = request.toCommand()
        val result = transferUseCase(command)

        return ResponseEntity.ok(result)
    }
}