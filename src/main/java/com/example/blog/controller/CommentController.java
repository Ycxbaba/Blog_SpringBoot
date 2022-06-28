package com.example.blog.controller;

import com.example.blog.entity.bean.Comment;
import com.example.blog.entity.query.QueryComment;
import com.example.blog.entity.result.Result;
import com.example.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Resource(name = "commentService")
	private CommentService commentService;

	@GetMapping("/{id}")
	public Result getComment(@PathVariable("id") int blogId){
		QueryComment queryComment = new QueryComment();
		queryComment.setBlogId(blogId);
		queryComment.setDeleted(false);
		return commentService.getComment(queryComment);
	}

	@PostMapping("/reply")
	public Result saveComment(@RequestBody Comment comment){
		return commentService.saveComment(comment);
	}
}
