package com.example.blog.service;

import com.example.blog.entity.bean.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.query.QueryComment;
import com.example.blog.entity.result.Result;

/**
* @author 24933
* @description 针对表【t_comment】的数据库操作Service
* @createDate 2022-06-01 17:05:31
*/
public interface CommentService extends IService<Comment> {

	Result getComment(QueryComment queryComment);



	Result delete(int id);

	Result delComment(int id);

	Result saveComment(Comment comment);
}
