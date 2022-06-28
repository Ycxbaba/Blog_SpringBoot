package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.Message;
import com.example.blog.entity.query.QueryMessage;
import com.example.blog.entity.result.Result;
import com.example.blog.exception.CommonException;
import com.example.blog.service.MessageService;
import com.example.blog.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 24933
* @description 针对表【t_message】的数据库操作Service实现
* @createDate 2022-06-28 21:38:33
*/
@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

	@Override
	public Result getMessages(QueryMessage queryMessage) {
		LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Message::getDeleted,queryMessage.getDeleted());
		List<Message> list = super.list(queryWrapper);
		return Result.success(list);
	}

	@Override
	public Result reply(Message message) {
		if (!super.save(message)) {
			throw new CommonException("600","回复失败");
		}
		return Result.success("回复成功",null);
	}

	@Override
	public Result deleteMessage(Integer id, boolean b) {
		if (b){
			if (!super.removeById(id)) {
				throw new CommonException("600","删除失败");
			}
		}else {
			Message message = new Message();
			message.setId(id);
			message.setDeleted(1);
			if(!super.updateById(message)){
				throw new CommonException("600","删除失败");
			}
		}
		return Result.success("删除成功",null);
	}

	@Override
	public Result recoverMessage(Integer id) {
		Message message = new Message();
		message.setId(id);
		message.setDeleted(1);
		if (!super.updateById(message)) {
			throw new CommonException("600","恢复失败");
		}
		return Result.success("恢复成功",null);
	}
}




