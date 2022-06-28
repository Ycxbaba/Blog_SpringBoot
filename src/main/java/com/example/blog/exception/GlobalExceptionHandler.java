package com.example.blog.exception;

import com.example.blog.entity.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

	@ExceptionHandler(value = CommonException.class)
	@ResponseBody
	public Result handle(CommonException e){
		log.error("出现了 ===> {} 的错误",e.getMsg());
		return Result.fail(e.getCode(),e.getMsg());
	}

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public Result handle(RuntimeException e){
		log.error("出现了 ===> { } 的错误",e.getCause());
		return Result.fail("500","服务器内部错误");
	}

}
