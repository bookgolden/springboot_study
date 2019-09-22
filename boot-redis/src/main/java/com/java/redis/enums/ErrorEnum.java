package com.java.redis.enums;

public interface ErrorEnum {

    public static final String EMPTY_STRING = "";

    public static final String SUCCESS = "success";

    public static final String FAILURE = "failure";

    public static final String OK = "OK";

    public static final String TRUE = "true";

    public static final String FALSE = "false";

    public static final String ERROR = "ERROR";

    String getErrorCode();

    String getErrorMsg();

}
