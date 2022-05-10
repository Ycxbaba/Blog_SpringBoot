package com.example.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.User;
import com.example.blog.entity.result.Result;
import com.example.blog.service.UserService;
import com.example.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 24933
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-05-10 21:16:49
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

	@Autowired
	private UserMapper userMapper;

}




