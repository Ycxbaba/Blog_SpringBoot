package com.example.blog.service.impl;

import com.example.blog.entity.bean.User;
import com.example.blog.entity.bean.UserDetailImpl;
import com.example.blog.entity.result.Result;

import com.example.blog.service.LoginService;
import com.example.blog.utils.JwtUtils;
import com.example.blog.utils.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

	@Resource(type = JwtUtils.class)
	private JwtUtils jwtUtils;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager;

	@Resource(type = RedisUtils.class)
	private RedisUtils redisUtils;

	/**
	 * 处理登录服务，保存登录状态
	 * @param user 获得的请求对象
	 */
	@Override
	public Result signin(User user) {
		//去数据库查询
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		if (authenticate == null) {
			throw new RuntimeException("验证失败");
		}
		//得到结果
		UserDetailImpl principal = (UserDetailImpl) authenticate.getPrincipal();
		User principalUser = principal.getUser();
		//根据id生成token,并保存到redis中
		Integer id = principalUser.getId();
		String token = jwtUtils.createToken(Long.valueOf(id));
		redisUtils.set("user" + id,principal,7, TimeUnit.DAYS);
		//将user的基本信息返回
		principalUser.setPassword("");
		Map<String,Object> map = new HashMap<>();
		map.put("token",token);
		map.put("user",principalUser);
		return Result.success(map);
	}

	/**
	 * 处理登出服务
	 * @param id 用户id
	 */
	@Override
	public Result signout(String id) {
		String key = "user"+id;
		if (!redisUtils.hasKey(key)) {
			throw new RuntimeException("已经登出了");
		}
		redisUtils.del(key);
		return Result.success("登出成功",null);
	}
}
