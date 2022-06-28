package com.example.blog.exception;

import com.alibaba.fastjson.JSON;
import com.example.blog.entity.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		String msg = "未捕获的错误";
		if(authException instanceof BadCredentialsException) {
			msg = "用户名或密码错误";
		}else if(authException instanceof UsernameNotFoundException){
			msg = "未找到该用户";
		}else if(authException instanceof InsufficientAuthenticationException){
			msg = "需要身份权限";
		}
		Result result = Result.fail("403", msg);
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(HttpStatus.OK.value());
		PrintWriter writer = response.getWriter();
		writer.write(JSON.toJSONString(result));
		writer.flush();
	}
}
