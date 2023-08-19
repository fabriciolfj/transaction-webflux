package com.github.transaction.providers.message.sendcashback;

import com.github.transaction.entities.TransactionMovementEntity;

public class CashbackConverterDTO {

    private CashbackConverterDTO() { }

    public static CasbhbackDTO toDTO(final TransactionMovementEntity entity) {
        return new CasbhbackDTO(entity.getTransaction(), entity.getTotal());
    }
}
