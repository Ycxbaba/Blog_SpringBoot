package com.example.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component(value = "redisUtils")
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

	/**
	 * 向set里放入数据
	 * @param key set key
	 * @param value value
	 * @return
	 */
	public boolean sAdd(String key,Object value){
		try {
			redisTemplate.opsForSet().add(key,value);
			return true;
		}catch (Exception e){
			log.warn("ip地址--{}--添加失败",value);
			return false;
		}
	}

	/**
	 * 从 set 中 拿到 所有value
	 * @param key key
	 * @return 返回set集合
	 */
	public Set<Object> sGet(String key){
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 *  zSet 保存值
	 */
	public boolean zsAdd(String key,Object value){
		try {
			redisTemplate.opsForZSet().incrementScore(key,value,1);
			return true;
		}catch (Exception e){
			log.warn("{}--{}--添加失败",key,value);
			return false;
		}
	}

	public Set<ZSetOperations.TypedTuple<Object>> zsGet(String key){
		return	redisTemplate.opsForZSet().rangeByScoreWithScores(key, 0, 1000);
	}
}
