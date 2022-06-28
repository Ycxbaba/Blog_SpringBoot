package com.example.blog.mapper;

import com.example.blog.entity.bean.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 24933
* @description 针对表【t_message】的数据库操作Mapper
* @createDate 2022-06-28 21:38:33
* @Entity com.example.blog.entity.bean.Message
*/
@Repository("messageMapper")
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}




