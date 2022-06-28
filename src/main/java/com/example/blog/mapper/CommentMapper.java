package com.example.blog.mapper;

import com.example.blog.entity.bean.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.query.QueryComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 24933
* @description 针对表【t_comment】的数据库操作Mapper
* @createDate 2022-06-01 17:05:31
* @Entity com.example.blog.entity.bean.Comment
*/
@Mapper
@Repository(value = "commentMapper")
public interface CommentMapper extends BaseMapper<Comment> {

	List<Comment> getComment(QueryComment queryComment);
}




