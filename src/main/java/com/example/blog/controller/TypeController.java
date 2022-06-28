package com.example.blog.controller;

import com.example.blog.annotation.RAnnotation;
import com.example.blog.entity.query.QueryType;
import com.example.blog.entity.result.Result;
import com.example.blog.service.TypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/type")
public class TypeController {

	@Resource(name = "typeService")
	private TypeService typeService;

	@RAnnotation(key = "typeList",time = 10)
	@PostMapping("/list")
	public Result getTypes(@RequestBody QueryType queryType){
		return typeService.getTypes(queryType);
	}
}
