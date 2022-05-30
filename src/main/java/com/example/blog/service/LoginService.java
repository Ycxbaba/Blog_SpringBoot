package com.example.blog.service;

import com.example.blog.entity.bean.User;
import com.example.blog.entity.result.Result;

public interface LoginService {
	Result signin(User user);

	Result signout(String id);

}
