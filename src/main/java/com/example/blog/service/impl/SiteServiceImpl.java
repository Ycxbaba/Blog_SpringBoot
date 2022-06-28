package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.Site;
import com.example.blog.entity.result.Result;
import com.example.blog.exception.CommonException;
import com.example.blog.service.SiteService;
import com.example.blog.mapper.SiteMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
* @author 24933
* @description 针对表【t_site】的数据库操作Service实现
* @createDate 2022-06-26 18:54:39
*/
@Service(value = "siteService")
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site>
    implements SiteService{

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
}




