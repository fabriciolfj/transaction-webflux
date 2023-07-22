package com.github.transaction.entities;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum TransactionType {

    CREDIT("credit"),
    DEBIT("debit"),
    VOUCHER("voucher");

    private String describe;

    public TransactionType toEnum(final String describe) {
        return Stream.of(values())
                .filter(t -> t.describe.equals(describe))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
