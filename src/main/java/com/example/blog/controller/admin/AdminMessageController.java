package com.example.blog.controller.admin;

import com.example.blog.entity.bean.Message;
import com.example.blog.entity.query.QueryMessage;
import com.example.blog.entity.result.Result;
import com.example.blog.service.MessageService;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/message")
public class AdminMessageController {
	@Resource(name = "messageService")
	private MessageService messageService;

	@PreAuthorize("hasAnyAuthority('superAdmin')")
	@PostMapping("/list")
	public Result getList(@RequestBody QueryMessage queryMessage){
		return messageService.getMessages(queryMessage);
	}

	@PreAuthorize("hasAnyAuthority('superAdmin')")
	@PutMapping("/delete/{id}")
	public Result update(@PathVariable Integer id){
		return messageService.deleteMessage(id,false);
	}

	@PreAuthorize("hasAnyAuthority('superAdmin')")
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable Integer id){
		return messageService.deleteMessage(id,true);
	}

	@PreAuthorize("hasAnyAuthority('superAdmin')")
	@GetMapping("/recover/{id}")
	public Result recover(@PathVariable Integer id){
		return messageService.recoverMessage(id);
	}
}
