package com.github.accounting.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends ServiceException {

    public InvalidParameterException(String message) {
        super(message);
        setData();
    }

    public InvalidParameterException(String message, Throwable throwable) {
        super(message, throwable);
        setData();
    }

    private void setData() {
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorCode(BizErrorCode.INVALID_PARAMETER);
        this.setErrorType(ErrorType.Client);
    }
}
