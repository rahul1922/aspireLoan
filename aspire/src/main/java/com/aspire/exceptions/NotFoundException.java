package com.aspire.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
