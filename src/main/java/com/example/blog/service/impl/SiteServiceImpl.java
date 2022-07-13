package com.example.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.Blog;
import com.example.blog.entity.bean.Site;
import com.example.blog.entity.bean.Type;
import com.example.blog.entity.result.Result;
import com.example.blog.exception.CommonException;
import com.example.blog.service.*;
import com.example.blog.mapper.SiteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
* @author 24933
* @description 针对表【t_site】的数据库操作Service实现
* @createDate 2022-06-26 18:54:39
*/
@Service(value = "siteService")
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site>
    implements SiteService{

	@Resource(name = "blogService")
	private BlogService blogService;

	@Resource(name = "typeService")
	private TypeService typeService;

	@Resource(name = "messageService")
	private MessageService messageService;

	@Resource(name = "commentService")
	private CommentService commentService;


	@Override
	public Result initSite() {
		QueryWrapper<Site> siteQueryWrapper = new QueryWrapper<>();
		Site site = super.getOne(siteQueryWrapper);
		site.setOngoings(new ArrayList<>(Arrays.asList(site.getOngoing().split(","))));
		site.setStacks(new ArrayList<>(Arrays.asList(site.getStack().split(","))));
		return Result.success(site);
	}


	@Override
	public Result updateSite(Site site) {
		StringJoiner sj = new StringJoiner(",");
		for (String ongoing : site.getOngoings()) {
			sj.add(ongoing);
		}
		site.setOngoing(sj.toString());
		sj = new StringJoiner(",");
		for (String stack : site.getStacks()) {
			sj.add(stack);
		}
		site.setStack(sj.toString());
		if (!super.updateById(site)) {
			throw new CommonException("600","修改失败");
		}
		return Result.success("修改完成",site);
	}

	@Override
	public Result getAllSite() {
		//对博客进行查询
		long blogCount = blogService.count();
		//对标签进行查询
		long typeCount = typeService.count();
		//对评论进行查询
		long commentCount = commentService.count();
		//对留言进行查询
		long messageCount = messageService.count();

		Site site = super.getById(1);

		HashMap<String,Long> map = new HashMap<>();
		map.put("blogCount",blogCount);
		map.put("typeCount",typeCount);
		map.put("commentCount",commentCount);
		map.put("messageCount",messageCount);
		map.put("viewCount",site.getViews().longValue());
		map.put("likeCount",site.getLikes().longValue());
		String string = JSON.toJSONString(map);
		return Result.success(string);
	}

}




