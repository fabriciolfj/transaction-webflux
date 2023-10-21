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
    private StatusTransaction status;
    private boolean isUseCashback;
    private BigDecimal cashback;

    public void addDiscount(final BigDecimal value) {
        this.balance = this.total.subtract(value);
        this.cashback = value;
    }

    public void addTotal(final BigDecimal value) {
        this.total.add(total.multiply(value));
    }

    public void setStatus(final StatusTransaction status) {
        this.status = status;
    }
}