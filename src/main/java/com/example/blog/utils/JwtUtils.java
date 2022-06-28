package com.example.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component(value = "jwtUtils")
@Slf4j
public class JwtUtils {

	@Value("${jwt.data.SECRET}")
	public String SECRET;

	@Value("${jwt.data.tokenHeader}")
	private String header;

	@Value("${jwt.data.expiration}")
	private int expiration;

	/**
	 * 创建token
	 * @return 加密字符串
	 */
	public String createToken(Long userId){
		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.SECOND,expiration);
		JwtBuilder builder = Jwts.builder()
				.setHeaderParam("typ","JWT")
				.setSubject(userId+"")
				.setIssuedAt(new Date())
				.setExpiration(calendar.getTime())
				.signWith(SignatureAlgorithm.HS256,SECRET);
		return builder.compact();
	}

	/**
	 * 校验token
	 */
	public Claims parseToken(String token){
		try{
			return Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token)
					.getBody();
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * 判断token是否过期
	 */
	public boolean judgeTokenExpiration(Date expiration){
		return expiration.before(new Date());
	}
}
