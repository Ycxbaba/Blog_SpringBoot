package com.example.blog.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.blog.annotation.RAnnotation;
import com.example.blog.entity.result.Result;
import com.example.blog.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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

	/**
	 * 统计点赞量
	 * @param joinPoint
	 */
	@Before("blogLike()")
	public void blogLikeCount(JoinPoint joinPoint){
		int id = (int) joinPoint.getArgs()[0];
		redisUtils.zsAdd("blogLike",id);
	}

	/**
	 * 缓存
	 * @param pjp ProceedingJoinPoint
	 * @param rAnnotation 自定义注解 保存 key 和 缓存 时间
	 */
	@Around(value = "@annotation(rAnnotation)",argNames = "pjp,rAnnotation")
	public Object redisCache(ProceedingJoinPoint pjp, RAnnotation rAnnotation){
		String key = rAnnotation.key();
		String args = Arrays.toString(pjp.getArgs());
		key = key + ":" + args;
		if (redisUtils.hasKey(key)) {
			return redisUtils.get(key);
		}else {
			try {
				int time = rAnnotation.time();
				//执行目标方法
				Result proceed = (Result) pjp.proceed();
				redisUtils.set(key,proceed,time, TimeUnit.MINUTES);
				return proceed;
			} catch (Throwable e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

}
