package com.example.blog.mapper;

import com.example.blog.entity.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 24933
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-05-10 21:16:49
* @Entity com.example.blog.entity.bean.User
*/
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




