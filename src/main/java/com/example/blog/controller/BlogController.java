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
	 * 分页查询 前端
	 * @param pageSize 每页大小
	 * @param pageNum 当前页码
	 */
	@GetMapping("/list")
	public Result getBlogList(@RequestParam(name = "pageSize",defaultValue = "8")int pageSize,
	                          @RequestParam(name = "pageNum",defaultValue = "1")int pageNum){
		QueryBlog queryBlog = new QueryBlog();
		queryBlog.setPageNum(pageNum);
		queryBlog.setPageSize(pageSize);
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
	@GetMapping("/like/{id}")
	public Result like(@PathVariable("id") int id){
		return Result.success("ok");
	}

}
