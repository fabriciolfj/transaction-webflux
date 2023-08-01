package com.github.transaction.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(final String msg) {
        super(msg);
    }
}
