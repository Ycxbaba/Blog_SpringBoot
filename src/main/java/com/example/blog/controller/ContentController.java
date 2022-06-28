package com.example.blog.controller;

import com.example.blog.annotation.RAnnotation;
import com.example.blog.entity.result.Result;
import com.example.blog.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content")
public class ContentController {

	@Resource(name = "contentService")
	private ContentService contentService;

	/**
	 * 查询内容
	 * @param id
	 * @return
	 */
	@RAnnotation(key = "content",time = 20)
	@GetMapping("/{id}")
	public Result getContent(@PathVariable("id")int id){
		return contentService.getContent(id);
	}

}
