package com.github.transaction.exceptions.enums;

import java.util.ResourceBundle;

public enum ErrorEnum {

    TRANSACTION_NOT_FOUND,
    INVALID_TOKEN;

    public String getMessage() {
        var bundle = ResourceBundle.getBundle("exceptions/messages");
        return bundle.getString(this.name() + ".message");
    }
}
