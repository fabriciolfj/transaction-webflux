package com.github.transaction.entrypoints.controller.transaction.converters;

import com.github.transaction.entities.*;
import com.github.transaction.entrypoints.controller.transaction.dto.TransactionRequest;
import com.github.transaction.entrypoints.controller.transaction.dto.TransactionResponse;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionConverter {

    private TransactionConverter() { }

    public static TransactionMovementEntity toEntity(final TransactionRequest request) {
        return new TransactionMovementEntity(
                new CustomerEntity(request.getCustomer()),
                TransactionEntity
                        .builder()
                        .status(StatusTransaction.PENDING)
                        .balance(request.getTotal())
                        .total(request.getTotal())
                        .type(TransactionType.valueOf(request.getType()))
                        .code(UUID.randomUUID().toString())
                        .discount(BigDecimal.ZERO)
                        .build(),
                new TokenValidationEntity().generetedToken());
    }

    public static TransactionResponse toResponse(final TransactionMovementEntity entity) {
        return TransactionResponse
                .builder()
                .code(entity.getTokenValue())
                .build();
    }
}
