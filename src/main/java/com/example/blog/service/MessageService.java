package com.example.blog.service;

import com.example.blog.entity.bean.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.query.QueryMessage;
import com.example.blog.entity.result.Result;

/**
* @author 24933
* @description 针对表【t_message】的数据库操作Service
* @createDate 2022-06-28 21:38:33
*/
public interface MessageService extends IService<Message> {

	Result getMessages(QueryMessage queryMessage);

	Result reply(Message message);

	Result deleteMessage(Integer id, boolean b);

	Result recoverMessage(Integer id);
}
