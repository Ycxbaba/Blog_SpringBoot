package com.example.blog.controller.admin;

import com.example.blog.entity.bean.Content;
import com.example.blog.entity.result.Result;
import com.example.blog.service.ContentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/content")
public class AdminContentController {

	@Resource(name = "contentService")
	private ContentService contentService;

	/**
	 * 获得博文 --- 后端
	 * @param id id
	 */
	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@GetMapping("/{id}")
	public Result getContent(@PathVariable("id")int id){
		return contentService.getContentNoCatalog(id);
	}

	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@PostMapping ("/save")
	public Result saveContent(@RequestBody Content content){
		return contentService.saveContent(content);
	}

}
