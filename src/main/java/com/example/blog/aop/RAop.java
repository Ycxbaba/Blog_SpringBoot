package com.example.blog.aop;

import com.example.blog.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
@Slf4j
public class RAop {

	@Resource(name = "redisUtils")
	private RedisUtils redisUtils;

	@Pointcut("execution(* com.example.blog.controller.BlogController.getBlog(int))")
	public void blogView(){
	}

	@Pointcut("execution(* com.example.blog.controller.BlogController.like(int))")
	public void blogLike(){
	}

	/**
	 * 统计访问量
	 * @param joinPoint
	 */
	@Before("blogView()")
	public void blogViewCount(JoinPoint joinPoint){
		int id = (int) joinPoint.getArgs()[0];
		redisUtils.zsAdd("blogView",id);
	}

	@Before("blogLike()")
	public void blogLikeCount(JoinPoint joinPoint){
		int id = (int) joinPoint.getArgs()[0];
		redisUtils.zsAdd("blogLike",id);
	}
}
