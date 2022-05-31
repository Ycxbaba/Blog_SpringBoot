package com.example.blog.controller;

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

	@GetMapping("/{id}")
	public Result getContent(@PathVariable("id")int id){
		return contentService.getContent(id);
	}

}
