package com.github.transaction.entities;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TransactionEntity{

    @EqualsAndHashCode.Include
    private String code;
    @EqualsAndHashCode.Include
    private TransactionType type;
    private BigDecimal total;
    private BigDecimal discount;
    private BigDecimal balance;

    public TransactionEntity applyDiscount(final BigDecimal value) {
        this.balance = this.total.subtract(value);
        return this;
    }
}