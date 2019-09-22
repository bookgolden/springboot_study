package com.java.redis.util;

import com.java.redis.enums.CacheErrorEnum;
import com.java.redis.enums.ErrorEnum;
import com.java.redis.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.concurrent.Callable;

@Slf4j
public class ReturnUtil {

    public static <T> DataModel<T> successResult(T data) {
        return new DataModel<>(CacheErrorEnum.SUCCESS, "", "", data);
    }

    public static <T> DataModel<T> successResult() {
        return new DataModel<>(CacheErrorEnum.SUCCESS, "", "", null);
    }

    public static <T> DataModel<T> exception(ErrorEnum error) {
        if (error == null) {
            return exception(CacheErrorEnum.ERROR_3004.getErrorCode(), CacheErrorEnum.ERROR_3004.getErrorMsg());
        }
        return exception(error.getErrorCode(), error.getErrorMsg());
    }

    public static <T> DataModel<T> exception(String errorCode, String errorMsg) {
        return new DataModel<>(CacheErrorEnum.FAILURE, errorCode, errorMsg, null);
    }

    public static <T> T getData(DataModel<T> dataModel) {
        if (dataModel == null) {
            throw new BusinessException(CacheErrorEnum.ERROR_3004);
        }
        if (CacheErrorEnum.SUCCESS.equals(dataModel.getResult())) {
            return dataModel.getData();
        } else if (!StringUtils.isEmpty(dataModel.getErrorCode()) && !StringUtils.isEmpty(dataModel.getErrorMsg())) {
            throw new BusinessException(dataModel.getErrorCode(), dataModel.getErrorMsg());
        }
        throw new BusinessException(CacheErrorEnum.ERROR_3004);
    }

    public static <T> T extractData(DataModel<T> dataModel) {
        if (dataModel == null) {
            return null;
        }
        return dataModel.getData();
    }

    /**
     * 获取结果（忽略异常）
     * @param callable
     * @param <T>
     * @return
     */
    public static <T> T extractData(Callable<DataModel<T>> callable) {
        try {
            DataModel<T> model = callable.call();
            return model == null ? null : model.getData();
        } catch (Exception e) {
            log.error("call exception", e);
            return null;
        }
    }

    public static <T> DataModel<T> getGenericsDataModel(DataModel dataModel) {
        return new DataModel<>(dataModel.getResult(), dataModel.getErrorCode(), dataModel.getErrorMsg(), null);
    }
}
