package com.github.transaction.entities;

import lombok.AllArgsConstructor;
import java.util.stream.Stream;

@AllArgsConstructor
public enum TransactionType {

    CREDIT("credit"),
    DEBIT("debit"),
    VOUCHER("voucher");

    private String describe;

    public static TransactionType toEnum(final String describe) {
        return Stream.of(values())
                .filter(t -> t.describe.equals(describe))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public String getDescribe() {
        return describe;
    }
}
