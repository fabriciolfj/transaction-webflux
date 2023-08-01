package com.github.transaction.providers.database.converter;

import com.github.transaction.entities.TransactionMovementEntity;
import com.github.transaction.providers.database.data.TransactionData;

public class TransactionDataConverter {

    private TransactionDataConverter() { }

    public static TransactionData toData(final TransactionMovementEntity entity) {
        return TransactionData
                .builder()
                .code(entity.getTransaction())
                .token(entity.getTokenValue())
                .status(entity.getStatus().getDescribe())
                .type(entity.getTypeTransaction())
                .total(entity.getTotal())
                .balance(entity.getBalance())
                .discount(entity.getDiscount())
                .customer(entity.getCodeCustomer())
                .dateToken(entity.getDateTimeToken())
                .build();
    }
}
