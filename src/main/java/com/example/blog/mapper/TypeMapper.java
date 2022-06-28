package com.example.blog.mapper;

import com.example.blog.entity.bean.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 24933
* @description 针对表【t_type】的数据库操作Mapper
* @createDate 2022-06-01 13:32:37
* @Entity com.example.blog.entity.bean.Type
*/
@Mapper
@Repository(value = "typeMapper")
public interface TypeMapper extends BaseMapper<Type> {

}




