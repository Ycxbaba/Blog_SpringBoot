package com.example.blog.api;

import com.example.blog.entity.result.Result;
import com.example.blog.exception.CommonException;
import com.example.blog.utils.ApiUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Api {

	@Resource(name = "ApiUtils")
	private ApiUtils apiUtils;

	@GetMapping("/qq/{qq}")
	public Result getQQ(@PathVariable("qq")String qq){
		if(Strings.isEmpty(qq)){
			throw new CommonException("600","未输入正确的QQ");
		}
		String res = apiUtils.getQQ(qq);
		if(res == null){
			throw new CommonException("600","未查询到相关QQ");
		}
		Map<String,String> map = new HashMap<>();
		String[] strings = res.split(",");
		map.put("nickname",strings[0]);
		map.put("avatar",strings[1]);
		map.put("email",qq + "@qq.com");
		map.put("qq",qq);
		return Result.success(map);
	}
}
