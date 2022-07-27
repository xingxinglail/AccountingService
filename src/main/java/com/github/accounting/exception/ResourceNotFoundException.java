package com.github.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ServiceException {

    public ResourceNotFoundException(String message) {
        super(message);
        setData();
    }

    public ResourceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
        setData();
    }

    private void setData() {
        this.setStatusCode(HttpStatus.NOT_FOUND.value());
        this.setErrorCode(BizErrorCode.RESOURCE_NOT_FOUND);
        this.setErrorType(ErrorType.Client);
    }
}
