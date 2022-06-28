package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.Content;
import com.example.blog.entity.result.Result;
import com.example.blog.service.ContentService;
import com.example.blog.mapper.ContentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		wrapper.eq(Content::getId,id);
		Content content = super.getOne(wrapper);
		return Result.success(content);
	}

	/**
	 * 新增和修改
	 * @param content 内容
	 */
	@Override
	@Transactional
	public Result saveContent(Content content) {
		Integer id = content.getId();
		if(id == null){
			if (super.save(content)) {
				return Result.success("保存成功",content.getId());
			}
		}else {
			if (super.updateById(content)) {
				return Result.success("修改成功",content.getId());
			}
		}
		return Result.fail("600","操作失败");
	}

	/**
	 * 逻辑删除
	 * @param id id
	 */
	@Override
	public Result delContent(int id) {
		Content content = new Content();
		content.setDeleted(1);
		content.setId(id);
		if (super.updateById(content)) {
			return Result.success("删除成功",null);
		}
		return Result.fail("500","删除失败");
	}

	/**
	 * 永久删除
	 * @param id id
	 */
	@Override
	public Result delete(int id) {
		if (super.removeById(id)) {
			return Result.success("永久删除成功",null);
		}
		return Result.fail("500","永久删除失败");
	}
}




