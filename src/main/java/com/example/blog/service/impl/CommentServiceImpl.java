package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.Comment;
import com.example.blog.entity.query.QueryComment;
import com.example.blog.entity.result.Result;
import com.example.blog.service.CommentService;
import com.example.blog.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author 24933
* @description 针对表【t_comment】的数据库操作Service实现
* @createDate 2022-06-01 17:05:31
*/
@Service(value = "commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

	@Resource(name = "commentMapper")
	private CommentMapper commentMapper;

	/**
	 * 查询评论
	 * @param queryComment
	 * @return
	 */
	@Override
	public Result getComment(QueryComment queryComment) {
		List<Comment> comments = commentMapper.getComment(queryComment);
		for (Comment comment : comments) {
			List<Comment> list = handleParentComment(comment.getChildrenComment());
			if(list.size() != 0)comment.setChildrenComment(list);
		}
		return Result.success(comments);
	}

	/**
	 * 永久删除
	 * @param id
	 * @return
	 */
	@Override
	public Result delete(int id) {
		if (super.removeById(id)) {
			return Result.success("删除成功",null);
		}
		return Result.fail("600","删除失败");
	}

	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	@Override
	public Result delComment(int id) {
		LambdaUpdateWrapper<Comment> wrapper = new LambdaUpdateWrapper<>();
		wrapper.eq(Comment::getId,id).set(Comment::getDeleted,1);
		if (super.update(wrapper)) {
			return Result.success("删除成功",null);
		}
		return Result.fail("600","删除失败");
	}

	/**
	 * 发布评论
	 * @param comment
	 * @return
	 */
	@Override
	public Result saveComment(Comment comment) {
		if (super.save(comment)) {
			return Result.success("回复成功",null);
		}
		return Result.fail("600","回复失败");
	}

	/**
	 * 查找 parentID = 0 的父评论
	 * @param comments
	 * @return
	 */
	private List<Comment> handleParentComment(List<Comment> comments){
		List<Comment> parent = new ArrayList<>();
		for (Comment comment : comments) {
			if(comment.getParentId() == 0){
				parent.add(comment);
			}
		}
		comments.removeAll(parent);
		if(comments.size() == 0){
			return parent;
		}
		for (Comment comment : parent) {
			List<Comment> children = new ArrayList<>();
			handleChildComment(comments,comment.getNickname(),
					comment.getId(),children);
			comment.setChildrenComment(children);
		}
		return parent;
	}

	/**
	 *
	 * @param comments 原始评论集合
	 * @param parentName 父评论昵称
	 * @param parentId 父评论id
	 * @param children parentID = 0 的父评论 的子评论
	 */
	private void handleChildComment(List<Comment> comments,String parentName,
	                                         int parentId,List<Comment> children){
		List<Comment> temp = new ArrayList<>();
		for (Comment comment : comments) {
			if(comment.getParentId() == parentId){
				comment.setParentName(parentName);
				temp.add(comment);

			}
		}
		comments.removeAll(temp);
		children.addAll(temp);
		if(comments.size() == 0){
			return;
		}
		for (Comment comment : temp) {
			handleChildComment(comments,comment.getNickname()
					,comment.getId(),children);
		}
	}



}




