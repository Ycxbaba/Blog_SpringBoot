package com.example.blog.controller;

import com.example.blog.entity.bean.User;
import com.example.blog.entity.result.Result;
import com.example.blog.service.LoginService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class LoginController {

	@Resource(type = LoginService.class)
	private LoginService loginService;

	@PostMapping("/signin")
	public Result signin(@RequestBody User user){
		return loginService.signin(user);
	}

	@DeleteMapping("/signout")
	public Result signout(@RequestParam("id")String id){
		return loginService.signout(id);
	}
}
