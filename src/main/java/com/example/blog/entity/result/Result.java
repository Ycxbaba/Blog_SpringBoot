package com.example.blog.entity.result;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result {
	private String code;
	private String msg;
	private Object data;

	public Result(){

	}

	private Result(String code,String msg,Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static Result success(Object data){
		return new Result("200","一次成功的请求",data);
	}

	public static Result success(String msg,Object data){
		return new Result("200",msg,data);
	}
	public static Result fail(String code,String msg){
		return new Result(code,msg,null);
	}
}
