package com.java.redis.exception;

import com.java.redis.enums.ErrorEnum;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public BusinessException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getErrorCode();
        this.errorMsg = errorEnum.getErrorMsg();
    }

    public BusinessException(String message, String errorCode, String errorMsg) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(String message, Throwable cause, String errorCode, String errorMsg) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(Throwable cause, String errorCode, String errorMsg) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return String.format("errorCode = %s, errorMsg = %s", errorCode, errorMsg);
    }
}
