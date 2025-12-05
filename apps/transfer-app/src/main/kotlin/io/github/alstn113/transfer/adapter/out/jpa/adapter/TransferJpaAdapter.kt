package io.github.alstn113.transfer.adapter.out.jpa.adapter

import io.github.alstn113.transfer.adapter.out.jpa.repository.TransferJpaRepository
import io.github.alstn113.transfer.application.port.out.TransferRepository
import io.github.alstn113.transfer.domain.Transfer
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class TransferJpaAdapter(
    private val transferJpaRepository: TransferJpaRepository,
    private val em: EntityManager,
) : TransferRepository {

    override fun save(transfer: Transfer): Transfer {
        if (transfer.id == 0L) {
            val transferEntity = TransferMapper.mapToTransferEntity(transfer)
            val transferLogEntity = TransferMapper.mapToTransferLogEntity(transfer)
            em.persist(transferEntity)
            em.persist(transferLogEntity)

            return TransferMapper.mapToTransfer(transferEntity, transferLogEntity)
        }

        val transferEntity = transferJpaRepository.findByIdOrNull(transfer.id)
            ?: throw EntityNotFoundException()
        val transferLogEntity = TransferMapper.mapToTransferLogEntity(transfer)
        em.persist(transferLogEntity)

        return TransferMapper.mapToTransfer(transferEntity, transferLogEntity)
    }
}