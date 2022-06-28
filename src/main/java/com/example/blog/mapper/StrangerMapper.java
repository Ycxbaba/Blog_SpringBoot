package com.example.blog.mapper;

import com.example.blog.entity.bean.Stranger;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 24933
* @description 针对表【t_stranger】的数据库操作Mapper
* @createDate 2022-05-25 21:00:08
* @Entity com.example.blog.entity.bean.Stranger
*/
@Repository("strangerMapper")
@Mapper
public interface StrangerMapper extends BaseMapper<Stranger> {

}




