package com.java.redis.enums;

public enum CacheErrorEnum implements ErrorEnum {

    ID_CARD_EXPIRED("BANK_0001", "页面已过期，请重新认证"),
    ERROR_3004("3004", "加载失败，请稍后再试"),
    ERROR_1001("1001", "输入参数异常!"),


    ;

    private String errorCode;
    private String errorMsg;

    CacheErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

}
