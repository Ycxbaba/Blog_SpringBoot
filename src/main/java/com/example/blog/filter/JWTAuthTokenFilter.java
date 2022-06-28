package com.example.blog.filter;

import com.example.blog.entity.bean.UserDetailImpl;
import com.example.blog.exception.CommonException;
import com.example.blog.utils.JwtUtils;
import com.example.blog.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.lang.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component("JWTAuthTokenFilter")
public class JWTAuthTokenFilter extends OncePerRequestFilter {

	@Resource(name = "jwtUtils")
	private JwtUtils jwtUtils;

	@Resource(name = "redisUtils")
	private RedisUtils redisUtils;

	/**
	 * 每次请求要先验证token
	 * @param request 请求
	 * @param response 响应
	 * @param filterChain 过滤器链
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//请求头中获取 token
		String token = request.getHeader("token");
		if(Strings.hasText(token) && !"null".equals(token)){
			//解析token
			Claims claims = jwtUtils.parseToken(token);
			if(claims == null){
				filterChain.doFilter(request,response);
				return;
			}
			String id = claims.getSubject();
			//查询缓存
			UserDetailImpl userDetail = (UserDetailImpl) redisUtils.get("user" + id);
			if (Objects.isNull(userDetail)) {
				filterChain.doFilter(request,response);
				return;
			}
			//认证通过 将权限信息放入上下文中
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		//放行
		filterChain.doFilter(request,response);
	}
}
