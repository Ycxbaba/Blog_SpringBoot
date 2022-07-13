package com.example.blog.service;

import com.example.blog.entity.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.result.Result;
import org.springframework.lang.Nullable;

/**
* @author 24933
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-05-10 21:16:49
*/
public interface UserService extends IService<User> {


	Result delUserById(Integer id);

	Result updatePw(User user);

	Result getAllUser();

	Result updateUser(User user, String token);
	Result updateUser(User user);

	Result getSelf(Integer id, String token);

	Result getAllRoles();

	Result deleteById(Integer id);

	Result recover(Integer id);

	Result saveUser(User user);

}
