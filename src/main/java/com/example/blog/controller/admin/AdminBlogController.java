package com.example.blog.controller.admin;

import com.example.blog.entity.bean.Blog;
import com.example.blog.entity.query.QueryBlog;
import com.example.blog.entity.result.Result;
import com.example.blog.service.BlogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/blog")
public class AdminBlogController {

	@Resource(name = "blogService")
	private BlogService blogService;

	/**
	 * 后台查询列表
	 * @param queryBlog 查询类
	 */
	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@GetMapping("/list")
	public Result getBlog(@RequestBody QueryBlog queryBlog){
		return blogService.getBlogPage(queryBlog);
	}

	/**
	 * 新增 / 修改
	 * @param blog 修改和新增的内容
	 */
	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@PostMapping("/save")
	public Result saveBlog(@RequestBody Blog blog){
		return blogService.saveBlog(blog);
	}

	/**
	 * 永久删除 超级管理员权限
	 * @param id 博文 id
	 */
	@PreAuthorize("hasAuthority('superAdmin')")
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable("id")int id){
		return blogService.delete(id);
	}

	/**
	 * 逻辑删除
	 * @param id 博文id
	 */
	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@PutMapping("/delete/{id}")
	public Result delBlog(@PathVariable("id")int id){
		return blogService.delBlog(id);
	}

}
