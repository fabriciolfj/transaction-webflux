package com.github.transaction.providers.message.sendcashbackuse;

import com.github.transaction.entities.TransactionMovementEntity;

public class CashbackUseConverterDTO {

    private CashbackUseConverterDTO() {}

    public static CashbackUseDTO toDTO(final TransactionMovementEntity entity) {
        return CashbackUseDTO
                .builder()
                .customer(entity.getCodeCustomer())
                .build();
    }

}