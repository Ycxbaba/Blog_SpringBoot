package com.example.blog.controller;

import com.example.blog.entity.bean.User;
import com.example.blog.entity.result.Result;
import com.example.blog.service.LoginService;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public Result login(@RequestBody User user){
		return loginService.login(user);
	}
}
