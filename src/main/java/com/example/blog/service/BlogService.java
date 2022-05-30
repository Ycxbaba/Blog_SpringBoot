package com.example.blog.service;

import com.example.blog.entity.bean.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.query.QueryBlog;
import com.example.blog.entity.result.Result;
import org.springframework.stereotype.Service;

/**
* @author 24933
* @description 针对表【t_blog】的数据库操作Service
* @createDate 2022-05-25 15:59:45
*/
public interface BlogService extends IService<Blog> {

	Result getBlogPage(QueryBlog queryBlog);

	Result getBlogById(int id);

	int updateView(int id,int count);
}
