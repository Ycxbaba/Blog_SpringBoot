package com.example.blog.controller.admin;

import com.example.blog.entity.query.QueryComment;
import com.example.blog.entity.result.Result;
import com.example.blog.service.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {

	@Resource(name = "commentService")
	private CommentService commentService;

	/**
	 * 查询所有的评论
	 * @param queryComment
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@PostMapping("/list")
	public Result getCommentList(@RequestBody QueryComment queryComment){
		return commentService.getComment(queryComment);
	}

	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@PutMapping("/delete/{id}")
	public Result delComment(@PathVariable("id") int id){
		return commentService.delComment(id);
	}

	/**
	 * 永久删除
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('superAdmin')")
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable("id") int id){
		return commentService.delete(id);
	}
}
