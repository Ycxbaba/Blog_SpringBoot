package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.Blog;
import com.example.blog.entity.query.QueryBlog;
import com.example.blog.entity.result.Result;
import com.example.blog.service.BlogService;
import com.example.blog.mapper.BlogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 24933
* @description 针对表【t_blog】的数据库操作Service实现
* @createDate 2022-05-25 15:59:45
*/
@Service(value = "blogService")
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog>
    implements BlogService{

	@Resource(name = "blogMapper")
	private BlogMapper blogMapper;

	@Override
	public Result getBlogPage(QueryBlog queryBlog) {
		Page<Blog> blogPage = new Page<>(queryBlog.getPageNum(),queryBlog.getPageSize());
		LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Blog::getDeleted,false)
				.eq(Blog::getPublish,true);
		IPage<Blog> page = super.page(blogPage,queryWrapper);
		return Result.success(page);
	}

	/**
	 * 查询指定博文
	 * @param id 博文id
	 */
	@Override
	public Result getBlogById(int id) {
		LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(Blog::getDeleted,false)
				.eq(Blog::getPublish,true)
				.eq(Blog::getId,id);
		Blog blog = super.getOne(wrapper);
		return Result.success(blog);
	}

	@Override
	public int updateView(int id, int count) {
		Blog blog = new Blog();
		blog.setId(id);
		blog.setViews(count);
		blogMapper.update(blog);
		return 0;
	}
}




