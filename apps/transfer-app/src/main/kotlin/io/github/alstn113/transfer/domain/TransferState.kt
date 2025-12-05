package io.github.alstn113.transfer.domain

enum class TransferState(
    val description: String,
) {
    STARTED("이체 시작"),
    WITHDRAW_SUCCESS("출금 성공"),
    WITHDRAW_CHECK_REQUIRED("출금 결과 확인 필요"),
    DEPOSIT_CHECK_REQUIRED("입금 결과 확인 필요"),
    REFUND_NEEDED("출금 취소 필요"),
    FAILED("이체 실패"),
    COMPLETED("이체 성공");

    fun transitionTo(nextState: TransferState): TransferState {
        val isValid = TransferTransition.isValid(this, nextState)
        if (!isValid) {
            throw IllegalStateException("상태 전이 불가: $this -> $nextState")
        }
        return nextState
    }

    enum class TransferTransition(
        val from: TransferState,
        val to: TransferState,
        val description: String,
    ) {
        STARTED_TO_WITHDRAW_SUCCESS(STARTED, WITHDRAW_SUCCESS, "출금 성공"),
        STARTED_TO_WITHDRAW_CHECK_REQUIRED(STARTED, WITHDRAW_CHECK_REQUIRED, "출금 결과 모름"),
        STARTED_TO_FAILED(STARTED, FAILED, "출금 실패"),

        WITHDRAW_SUCCESS_TO_DEPOSIT_CHECK_REQUIRED(WITHDRAW_SUCCESS, DEPOSIT_CHECK_REQUIRED, "입금 결과 모름"),
        WITHDRAW_SUCCESS_TO_REFUND_NEEDED(WITHDRAW_SUCCESS, REFUND_NEEDED, "입금 실패"),
        WITHDRAW_SUCCESS_TO_COMPLETED(WITHDRAW_SUCCESS, COMPLETED, "입금 성공"),

        WITHDRAW_CHECK_REQUIRED_TO_FAILED(WITHDRAW_CHECK_REQUIRED, FAILED, "출금 확인 실패"),
        WITHDRAW_CHECK_REQUIRED_TO_REFUND_NEEDED(WITHDRAW_CHECK_REQUIRED, REFUND_NEEDED, "출금 성공 확인"),

        DEPOSIT_CHECK_REQUIRED_TO_REFUND_NEEDED(DEPOSIT_CHECK_REQUIRED, REFUND_NEEDED, "입금 실패 확인"),
        DEPOSIT_CHECK_REQUIRED_TO_COMPLETED(DEPOSIT_CHECK_REQUIRED, COMPLETED, "입금 성공 확인"),

        REFUND_NEEDED_TO_FAILED(REFUND_NEEDED, FAILED, "출금 취소");

        companion object {
            private val validTransitions = entries.map { it.from to it.to }.toSet()

            fun isValid(from: TransferState, to: TransferState): Boolean {
                return validTransitions.contains(from to to)
            }
        }
    }
}