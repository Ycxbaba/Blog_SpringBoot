package com.example.blog.controller;


import com.example.blog.entity.bean.Message;
import com.example.blog.entity.query.QueryMessage;
import com.example.blog.entity.result.Result;
import com.example.blog.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Resource(name = "messageService")
	private MessageService messageService;

	@GetMapping
	public Result getMessages(){
		QueryMessage queryMessage = new QueryMessage();
		queryMessage.setDeleted(0);
		return messageService.getMessages(queryMessage);
	}

	@PostMapping
	public Result replyMessage(@RequestBody Message message){
		return messageService.reply(message);
	}
}
