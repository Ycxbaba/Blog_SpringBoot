package com.example.blog.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

	@Resource(name = "template")
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 保存 值
	 * @param key 键
	 * @param object 值
	 * @return 是否成功
	 */
	public boolean set(String key,Object object){
		try {
			redisTemplate.opsForValue().set(key,object);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	/**
	 * 设置值
	 * @param key 键
	 * @param object 值
	 * @param time 过期时间
	 * @param timeUnit 过期时间单位
	 * @return 是否成功
	 */
	public boolean set(String key, Object object, int time, TimeUnit timeUnit){
		try {
			redisTemplate.opsForValue().set(key,object,time,timeUnit);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	/**
	 * 查询是否有值
	 * @param key 键
	 * @return 是否成功
	 */
	public boolean hasKey(String key){
		return Boolean.TRUE.equals(redisTemplate.hasKey(key));
	}

	/**
	 * 查询值
	 * @param key 键
	 * @return 返回键对应的值
	 */
	public Object get(String key){
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	/**
	 * 删除值
	 * @param key 键
	 * @return 是否成功
	 */
	public boolean del(String key){

		return Boolean.TRUE.equals(redisTemplate.delete(key));
	}

	/**
	 * 设置 hash
	 * @param key 键
	 * @param field 域
	 * @param value 值
	 * @return 是否成功
	 */
	public boolean set(String key,Object field,Object value){
		try {
			redisTemplate.opsForHash().put(key,field,value);
			return true;
		}catch (Exception e){
			return false;
		}
	}
}
