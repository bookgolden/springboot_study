package com.java.redis.util;

import lombok.Data;

@Data
public class DataModel<T> {

    private static final String CONS_SUCCESS = "success";
    private static final String STRING_EMPTY = "";

    private String result = STRING_EMPTY;
    private String errorCode = STRING_EMPTY;
    private String errorMsg = STRING_EMPTY;
    private T data;

    public DataModel(String result, String errorCode, String errorMsg, T data) {
        this.result = result;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static boolean isSuccess(DataModel dataModel) {

        return null != dataModel && CONS_SUCCESS.equals(dataModel.getResult());
    }

}
