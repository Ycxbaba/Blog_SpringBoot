package com.example.blog.mapper;

import com.example.blog.entity.bean.Content;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 24933
* @description 针对表【t_content】的数据库操作Mapper
* @createDate 2022-05-31 22:56:43
* @Entity com.example.blog.entity.bean.Content
*/
@Mapper
@Repository(value = "contentMapper")
public interface ContentMapper extends BaseMapper<Content> {

}




