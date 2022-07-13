package com.example.blog.service;

import com.example.blog.entity.bean.Site;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.result.Result;

/**
* @author 24933
* @description 针对表【t_site】的数据库操作Service
* @createDate 2022-06-26 18:54:39
*/
public interface SiteService extends IService<Site> {

	Result initSite();

	Result updateSite(Site site);

	Result getAllSite();
}
