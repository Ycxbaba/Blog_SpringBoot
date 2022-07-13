package com.example.blog.mapper;

import com.example.blog.entity.bean.Role;
import com.example.blog.entity.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 24933
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-05-10 21:16:49
* @Entity com.example.blog.entity.bean.User
*/
@Repository(value = "userMapper")
@Mapper
public interface UserMapper extends BaseMapper<User> {

	User selectByUsername(String username);

	User selectSelf(Integer id);

	List<User> selectAll();

	List<Role> getAllRoles();

	Boolean setRole(Integer roleId ,Integer userId);

	Boolean saveUR(Integer userId, Integer roleId);

	Boolean deleteUR(Integer id);
}




