package com.example.blog.service.impl;

import com.example.blog.entity.bean.User;
import com.example.blog.entity.result.Result;
import com.example.blog.mapper.UserMapper;
import com.example.blog.service.LoginService;
import com.example.blog.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;

@Service
public class LoginServiceImpl implements LoginService {

	@Resource(type = UserMapper.class)
	private UserMapper userMapper;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager;

	@Resource(type = RedisUtils.class)
	private RedisUtils redisUtils;

	/**
	 * 处理登录服务，保存登录状态
	 * @param user 获得的请求对象
	 */
	@Override
	public Result login(User user) {
		//去数据库查询
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		Authentication authenticate = authenticationManager.authenticate(authenticationToken);

		//得到结果
		User principal = (User) authenticate.getPrincipal();
		if(principal == null){
			throw new RuntimeException("查无此用户");
		}
		Integer id = principal.getId();
		redisUtils.set("user" + id,"ddd");
		return null;
	}
}
