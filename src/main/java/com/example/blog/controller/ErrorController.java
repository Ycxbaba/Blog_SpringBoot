package com.example.blog.controller;

import com.example.blog.entity.result.Result;
import com.example.blog.exception.CommonException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {

	@GetMapping("/expired")
	public void expired(){
		throw new CommonException("401","用户过期请重新登录");
	}
}
