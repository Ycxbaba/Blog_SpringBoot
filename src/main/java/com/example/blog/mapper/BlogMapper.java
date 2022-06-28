package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.entity.bean.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.query.QueryBlog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 24933
* @description 针对表【t_blog】的数据库操作Mapper
* @createDate 2022-05-25 15:59:45
* @Entity com.example.blog.entity.bean.Blog
*/
@Mapper
@Repository("blogMapper")
public interface BlogMapper extends BaseMapper<Blog> {

	int updateView(Blog blog);
	IPage<Blog> selectBlogPage(IPage<Blog> page, QueryBlog queryBlog);

	Blog getById(int id);
}




