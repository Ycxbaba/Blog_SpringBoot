package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.Blog;
import com.example.blog.entity.bean.Type;
import com.example.blog.entity.query.QueryType;
import com.example.blog.entity.result.Result;
import com.example.blog.mapper.BlogMapper;
import com.example.blog.service.BlogService;
import com.example.blog.service.TypeService;
import com.example.blog.mapper.TypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 24933
* @description 针对表【t_type】的数据库操作Service实现
* @createDate 2022-06-01 13:32:37
*/
@Service(value = "typeService")
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

	@Resource(name = "typeMapper")
	private TypeMapper typeMapper;

	@Resource(name = "blogService")
	private BlogService blogService;

	/**
	 * 查询所有标签
	 * @param queryType
	 */
	@Override
	public Result getTypes(QueryType queryType) {
		LambdaQueryWrapper<Type> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.ne(Type::getId,0);
		if(queryType != null){
			queryType.setDeleted(0);
			queryWrapper.eq(Type::getDeleted,queryType.getDeleted());
			if(queryType.getPageNum() != null){
				queryWrapper.orderBy(true,false,Type::getUpdateTime);
				IPage<Type> typeIPage = new Page<>(queryType.getPageNum(),queryType.getPageSize());
				IPage<Type> page = super.page(typeIPage, queryWrapper);
				return Result.success(page);
			}
		}
		List<Type> list = super.list(queryWrapper);
		return Result.success(list);
	}

	/**
	 * 修改 | 新增
	 * @param type
	 * @return
	 */
	@Override
	public Result saveType(Type type) {
		Integer id = type.getId();
		if ( id != null) {
			if (super.updateById(type)) {
				return Result.success("修改成功",null);
			}
		}else {
			if (super.save(type)) {
				return Result.success("新增成功",null);
			}
		}
		return Result.fail("600","操作失败");
	}

	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	@Override
	public Result delType(int id) {
		LambdaUpdateWrapper<Type> updateWrapper = new LambdaUpdateWrapper<>();
		updateWrapper.set(Type::getDeleted,1).eq(Type::getId,id);
		return super.update(updateWrapper)
				? Result.success("删除成功",null)
				: Result.fail("600","删除失败");
	}

	/**
	 * 永久删除
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public Result delete(int id) {
		LambdaUpdateWrapper<Blog> wrapper = new LambdaUpdateWrapper<>();
		wrapper.set(Blog::getTypeId,0).eq(Blog::getTypeId,id);
		blogService.update(wrapper);
		return super.removeById(id)
				? Result.success("删除成功",null)
				: Result.fail("600","删除失败");
	}
}




