package com.github.transaction.entrypoints.controller.approvetransaction.converters;

import com.github.transaction.entities.AuthorizeTransactionEntity;
import com.github.transaction.entrypoints.controller.approvetransaction.dto.ApproveTransactionDTO;

public class ApproveTransactionConverter {

    private ApproveTransactionConverter() { }

    public static AuthorizeTransactionEntity toEntity(final ApproveTransactionDTO dto) {
        return AuthorizeTransactionEntity.builder()
                .code(dto.getCode())
                .token(dto.getToken())
                .build();
    }
}
