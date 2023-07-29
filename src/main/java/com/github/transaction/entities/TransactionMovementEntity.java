package com.github.transaction.entities;


import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionMovementEntity{

    private CustomerEntity customer;
    private TransactionEntity transaction;
    private TokenValidationEntity token;

    public TransactionMovementEntity applyRate(final BigDecimal value) {
        this.transaction.addTotal(value);
        return this;
    }

    public String getTransaction() {
        return this.transaction.getCode();
    }
}
