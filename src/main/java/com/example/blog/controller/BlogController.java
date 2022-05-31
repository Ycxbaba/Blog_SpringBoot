package com.example.blog.controller;

import com.example.blog.annotation.RAnnotation;
import com.example.blog.entity.query.QueryBlog;
import com.example.blog.entity.result.Result;
import com.example.blog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog")
public class BlogController {

	@Resource(name = "blogService")
	private BlogService blogService;

	/**
	 * 分页查询 普通用户
	 */
	@GetMapping("/list")
	public Result getBlogList(@RequestBody QueryBlog queryBlog){
		queryBlog.setDeleted(false);
		queryBlog.setPublish(true);
		return blogService.getBlogPage(queryBlog);
	}

	/**
	 *  前端页面展示博文内容
	 */
	@GetMapping("/{id}")
	public Result getBlog(@PathVariable("id") int id){
		return blogService.getBlogById(id);
	}

	/**
	 * 点赞
	 * @param id 博文 id
	 */
	@PutMapping("/like/{id}")
	public Result like(@PathVariable("id") int id){
		return Result.success("ok");
	}



}
