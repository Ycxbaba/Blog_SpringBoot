package com.example.blog.service;

import com.example.blog.entity.bean.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.query.QueryType;
import com.example.blog.entity.result.Result;

/**
* @author 24933
* @description 针对表【t_type】的数据库操作Service
* @createDate 2022-06-01 13:32:37
*/
public interface TypeService extends IService<Type> {

	Result getTypes(QueryType queryType);

	Result saveType(Type type);

	Result delType(int id);

	Result delete(int id);
}
