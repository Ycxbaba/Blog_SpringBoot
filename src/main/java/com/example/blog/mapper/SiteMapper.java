package com.example.blog.mapper;

import com.example.blog.entity.bean.Site;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 24933
* @description 针对表【t_site】的数据库操作Mapper
* @createDate 2022-06-26 18:54:39
* @Entity com.example.blog.entity.bean.Site
*/
@Repository("siteMapper")
@Mapper
public interface SiteMapper extends BaseMapper<Site> {

}




