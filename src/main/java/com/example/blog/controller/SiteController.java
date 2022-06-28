package com.example.blog.controller;

import com.example.blog.entity.bean.Site;
import com.example.blog.entity.result.Result;
import com.example.blog.service.SiteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class SiteController {

	@Resource(name = "siteService")
	private SiteService siteService;

	@GetMapping("/site")
	public Result initSite(){
		return siteService.initSite();
	}

	@PreAuthorize("hasAuthority('superAdmin')")
	@PostMapping("/admin/site")
	public Result updateSite(@RequestBody Site site){
		return siteService.updateSite(site);
	}
}
