package com.github.transaction.exceptions.enums;

import java.util.ResourceBundle;

public enum ErrorEnum {

    INVALID_TOKEN;

    public String getMessage() {
        var bundle = ResourceBundle.getBundle("exceptions/messages");
        return this.name() + ".message";
    }
}
