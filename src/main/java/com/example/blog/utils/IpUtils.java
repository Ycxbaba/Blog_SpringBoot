package com.example.blog.utils;

import com.alibaba.fastjson.JSONObject;


import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@Component(value = "ipUtils")
public class IpUtils {

	/**
	 * 根据 ip查询地址
	 * @param ip 用户ip
	 * @return 返回的格式为 province,city
	 */
	public String getRealAddress(String ip){
		String url = "http://ip.ws.126.net/ipquery?ip=" + ip;
		String str = get(url);
		if(StringUtils.hasText(str)){
			String substring = str.substring(str.indexOf("{"), str.indexOf("}")+1);
			JSONObject jsonObject = JSONObject.parseObject(substring);
			String province = jsonObject.getString("province");
			String city = jsonObject.getString("city");
			return province + "," + city;
		}
		return null;
	}

	private String get(String url){
		HttpURLConnection connection = null;
		BufferedReader in = null;
		String address = "";
		try {
			StringBuilder result = new StringBuilder();
			URL url1  = new URL(url);
			connection = (HttpURLConnection) url1.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestMethod(HttpMethod.GET.name());
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GBK"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			address = result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return address;
	}
}
