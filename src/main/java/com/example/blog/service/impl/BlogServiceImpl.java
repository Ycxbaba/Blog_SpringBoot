package com.example.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.ArchiveBlog;
import com.example.blog.entity.bean.Blog;
import com.example.blog.entity.bean.Content;
import com.example.blog.entity.query.QueryBlog;
import com.example.blog.entity.result.Result;
import com.example.blog.exception.CommonException;
import com.example.blog.service.BlogService;
import com.example.blog.mapper.BlogMapper;
import com.example.blog.service.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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

	@Resource(name = "contentService")
	private ContentService contentService;

	/**
	 * 查询
	 * @param queryBlog 查询条件类
	 */
	@Override
	public Result getBlogPage(QueryBlog queryBlog) {
		Page<Blog> blogPage = new Page<>(queryBlog.getPageNum(),queryBlog.getPageSize());
		IPage<Blog> page = blogMapper.selectBlogPage(blogPage,queryBlog);
		return Result.success(page);
	}

	/**
	 * 查询指定博文
	 * @param id 博文id
	 */
	@Override
	public Result getBlogById(int id) {
		Blog blog = blogMapper.getById(id);
		Integer contentId = blog.getContentId();
		Result content = contentService.getContent(contentId);
		Content data = (Content) content.getData();
		blog.setContent(data);
		return Result.success(blog);
	}

	/**
	 * 更新 view
	 * @param id 博文id
	 * @param count 新增访问量
	 */
	@Override
	public int updateView(int id, int count) {
		Blog blog = new Blog();
		blog.setId(id);
		blog.setViews(count);
		return blogMapper.updateView(blog);
	}

	/**
	 * 新增/修改 博文
	 * @param blog 博文内容
	 */
	@Override
	@Transactional
	public Result saveBlog(Blog blog) {
		Content content = blog.getContent();
		if(content == null){
			throw new CommonException("600","内容为空");
		}
		if(blog.getId() == null){
			//新增
			//保存博客
			Result result = contentService.saveContent(content);
			if("200".equals(result.getCode())) {
				//保存信息
				Integer contentId = (Integer) result.getData();
				blog.setContentId(contentId);
				if (super.save(blog)) {
					return Result.success("新增成功", blog.getId());
				}else {
					throw new CommonException("600","保存失败");
				}
			}else {
				throw new CommonException("600","保存失败");
			}
		}else {
			content.setId(blog.getContentId());
			Result result = contentService.saveContent(content);
			//修改
			if("200".equals(result.getCode()) && super.updateById(blog)) {
				return Result.success("修改成功",blog.getId());
			}else throw new CommonException("600","修改失败");

		}
	}

	/**
	 * 永久删除 ---todo  待测试
	 * @param id 博文id
	 *
	 */
	@Override
	public Result delete(int id) {

		if (super.removeById(id)) {
			return Result.success("删除成功",null);
		}
		return Result.fail("600","删除失败");
	}

	/**
	 * 逻辑删除 ---todo  待测试
	 * @param id 博文id
	 */
	@Override
	public Result delBlog(int id) {
		Blog blog = new Blog();
		blog.setId(id);
		blog.setDeleted(1);
		if (super.updateById(blog)) {
			return Result.success("删除成功");
		}
		return Result.fail("600","删除失败");
	}

	/**
	 * 恢复
	 * @param id
	 * @return
	 */
	@Override
	public Result recover(int id) {
		Blog blog = new Blog();
		blog.setId(id);
		blog.setDeleted(0);
		super.updateById(blog);
		return Result.success("恢复成功",null);
	}

	@Override
	public Result getBlogArchive() {
		QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("deleted",0)
				.eq("publish",1)
				.orderBy(true,false,"create_time");
		List<Blog> list = super.list(queryWrapper);
		List<ArchiveBlog> blogs = handleDate(list);
		return Result.success(blogs);
	}

	private List<ArchiveBlog> handleDate(List<Blog> list){
		HashMap<Integer, HashMap<Integer, List<Blog>>> map = new HashMap<>();
		Calendar calendar = Calendar.getInstance();
		for (Blog blog : list) {
			calendar.setTime(blog.getCreateTime());
			Integer year = calendar.get(Calendar.YEAR);
			Integer month = calendar.get(Calendar.MONTH);
			if(map.containsKey(year)){
				HashMap<Integer, List<Blog>> hashMap = map.get(year);
				List<Blog> blogs = hashMap.containsKey(month)
						? hashMap.get(month) : new ArrayList<>();
				blogs.add(blog);
				hashMap.put(month,blogs);
			}else {
				List<Blog> blogs = new ArrayList<>();
				blogs.add(blog);
				HashMap<Integer, List<Blog>> hashMap = new HashMap<>();
				hashMap.put(month,blogs);
				map.put(year,hashMap);
			}
		}
		List<ArchiveBlog> res = new ArrayList<>();
		for (Map.Entry<Integer, HashMap<Integer, List<Blog>>> yearEntry : map.entrySet()) {
			ArchiveBlog archiveYear = new ArchiveBlog();
			Integer year = yearEntry.getKey();
			archiveYear.setDate(year);
			List<ArchiveBlog> archiveBlogs = new ArrayList<>();
			HashMap<Integer, List<Blog>> value = yearEntry.getValue();
			for (Map.Entry<Integer, List<Blog>> monthEntry : value.entrySet()) {
				ArchiveBlog archiveMonth = new ArchiveBlog();
				archiveMonth.setDate(monthEntry.getKey());
				archiveMonth.setBlogs(monthEntry.getValue());
				archiveBlogs.add(archiveMonth);
			}
			archiveBlogs.sort((o1, o2) -> o2.getDate() - o1.getDate());
			archiveYear.setArchives(archiveBlogs);
			res.add(archiveYear);
		}
		res.sort((o1, o2) -> o2.getDate() - o1.getDate());
		return res;
	}


}




