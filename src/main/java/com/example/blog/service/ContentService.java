package com.example.blog.service;

import com.example.blog.entity.bean.Content;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.result.Result;

/**
* @author 24933
* @description 针对表【t_content】的数据库操作Service
* @createDate 2022-05-31 22:56:43
*/
public interface ContentService extends IService<Content> {

	Result getContent(int id);

	Result getContentNoCatalog(int id);

	Result saveContent(Content content);

	Result delContent(int id);

	Result delete(int id);
}
