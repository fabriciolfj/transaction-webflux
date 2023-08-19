package com.github.transaction.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum StatusTransaction {

    PENDING("pending"),
    APPROVED("approved");

    private final String describe;

    public static StatusTransaction toEnum(final String describe) {
        return Stream.of(StatusTransaction.values())
                .filter(s -> s.describe.equals(describe))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}
