package com.github.transaction.exceptions.exceptions;

import com.github.transaction.exceptions.enums.ErrorEnum;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException() {
        super(ErrorEnum.TRANSACTION_NOT_FOUND.getMessage());
    }
}
