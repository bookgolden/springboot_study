package com.java.redis.exception;

import com.java.redis.enums.CacheErrorEnum;
import com.java.redis.util.DataModel;
import com.java.redis.util.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.exceptions.JedisClusterException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public DataModel<?> exceptionHandler(HttpServletRequest request, Exception ex) {
		if(ex instanceof BusinessException){
			BusinessException exception = (BusinessException)ex;
			exception.printStackTrace();
			return new DataModel<>("faliure", exception.getErrorCode(), exception.getErrorMsg(), "");
		} else if (ex instanceof ServletRequestBindingException || ex instanceof BindException) {
			return ReturnUtil.exception(CacheErrorEnum.ERROR_1001);
		}else if (ex instanceof IllegalArgumentException) {
			return ReturnUtil.exception(CacheErrorEnum.ERROR_3004);
		} else if(ex instanceof JedisClusterException){
			log.error("Redis集群异常, 异常信息 = {}", ex.getMessage());
			return ReturnUtil.exception(CacheErrorEnum.ERROR_3004);
		}
		log.error("异常信息 = {}", ex);
//		ex.printStackTrace();
		return ReturnUtil.exception(CacheErrorEnum.ERROR_3004);
	}
}
