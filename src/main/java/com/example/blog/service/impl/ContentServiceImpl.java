package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.Content;
import com.example.blog.entity.result.Result;
import com.example.blog.service.ContentService;
import com.example.blog.mapper.ContentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 24933
* @description 针对表【t_content】的数据库操作Service实现
* @createDate 2022-05-31 22:56:43
*/
@Service(value = "contentService")
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content>
    implements ContentService{

	@Resource(name = "contentMapper")
	private ContentMapper contentMapper;

	/**
	 * 获取 博文内容  需要生成目录
	 * @param id id
	 */
	@Override
	public Result getContent(int id) {
		return getContent(id,true);
	}

	/**
	 * 获取博文内容  不需要目录
	 * @param id id
	 */
	@Override
	public Result getContentNoCatalog(int id) {
		return getContent(id,false);
	}

	/**
	 * 博文内容
	 * @param id id
	 * @param hasCatalog 解析目录
	 */
	private Result getContent(int id , boolean hasCatalog){
		LambdaQueryWrapper<Content> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(Content::getBlogId,id);
		Content content = super.getOne(wrapper);
		// todo  解析出目录
		if(hasCatalog){
			System.out.println("1111");
		}else {
			return Result.success(content);
		}
		return Result.success(content);
	}

	/**
	 * 新增和修改
	 * @param content 内容
	 */
	@Override
	public Result saveContent(Content content) {
		Integer id = content.getId();
		if(id == null){
			if (super.save(content)) {
				return Result.success("保存成功",content.getId());
			}
		}else {
			if (super.updateById(content)) {
				return Result.success("修改成功",null);
			}
		}
		return Result.fail("500","操作失败");
	}
}




