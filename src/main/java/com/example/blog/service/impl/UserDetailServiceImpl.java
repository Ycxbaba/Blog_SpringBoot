package com.example.blog.service.impl;

import com.example.blog.entity.bean.User;
import com.example.blog.entity.bean.UserDetailImpl;
import com.example.blog.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Resource(name = "userMapper")
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//查询数据库
		User user = userMapper.selectByUsername(username);
		//封装为UserDetails
		UserDetailImpl userDetail = new UserDetailImpl();
		userDetail.setUser(user);

		return userDetail;
	}
}
