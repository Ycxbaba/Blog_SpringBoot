package com.example.blog.exception;

import com.alibaba.fastjson.JSON;
import com.example.blog.entity.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
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
		Result result = Result.fail("403", "权限不足");
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(HttpStatus.OK.value());
		PrintWriter writer = response.getWriter();
		writer.write(JSON.toJSONString(result));
		writer.flush();
	}
}
