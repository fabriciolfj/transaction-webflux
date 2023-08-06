package com.github.transaction.entities;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public String getTypeTransaction() {
        return this.transaction.getType().getDescribe();
    }

    public String getTokenValue() {
        return this.token.getCode();
    }

    public boolean isTokenValid(final String codeToken) {
        return this.token.isTokenValid() && this.token.isEqualToken(codeToken);
    }

    public TransactionMovementEntity aprrovedTransaction() {
        this.transaction.setStatus(StatusTransaction.APPROVED);
        return this;
    }

    public boolean isTransactionDebit() {
        return getTypeTransaction().equals(TransactionType.DEBIT.getDescribe());
    }

    public StatusTransaction getStatus() {
        return this.transaction.getStatus();
    }

    public BigDecimal getTotal() {
        return this.transaction.getTotal();
    }

    public BigDecimal getDiscount() {
        return this.transaction.getDiscount();
    }

    public BigDecimal getBalance() {
        return this.transaction.getBalance();
    }

    public String getCodeCustomer() {
        return this.customer.code();
    }

    public LocalDateTime getDateTimeToken() {
        return this.token.getDate();
    }
}
