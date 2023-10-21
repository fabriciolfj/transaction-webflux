package com.github.transaction.providers.database.persisttransaction;

import com.github.transaction.entities.*;

public class TransactionDataConverter {

    private TransactionDataConverter() { }

    public static TransactionMovementEntity toEntity(final TransactionData data) {
        return TransactionMovementEntity
                .builder()
                .transaction(toEntityTransaction(data))
                .token(toTokenEntity(data))
                .customer(toCustomerEntity(data))
                .build();

    }

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
                .cashback(entity.getCashback())
                .useCashback(entity.isUseCashback())
                .build();
    }

    private static CustomerEntity toCustomerEntity(final TransactionData data) {
        return new CustomerEntity(data.getCustomer());
    }

    private static TokenValidationEntity toTokenEntity(final TransactionData data) {
        return TokenValidationEntity
                .builder()
                .code(data.getToken())
                .date(data.getDateToken())
                .build();
    }

    private static TransactionEntity toEntityTransaction(final TransactionData data) {
        return TransactionEntity
                .builder()
                .balance(data.getBalance())
                .code(data.getCode())
                .discount(data.getDiscount())
                .total(data.getTotal())
                .status(StatusTransaction.toEnum(data.getStatus()))
                .type(TransactionType.toEnum(data.getType()))
                .cashback(data.getCashback())
                .isUseCashback(data.isUseCashback())
                .build();
    }
}
