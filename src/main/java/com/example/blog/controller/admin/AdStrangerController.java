package com.example.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.entity.bean.Stranger;
import com.example.blog.entity.result.Result;
import com.example.blog.service.StrangerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/stranger")
public class AdStrangerController {
	@Resource(name = "strangerService")
	private StrangerService strangerService;

	@PreAuthorize("hasAnyAuthority('admin','superAdmin')")
	@GetMapping("/all")
	public Result getAll(){
		return Result.success(strangerService.list());
	}

	@PreAuthorize("hasAnyAuthority('admin','superAdmin')")
	@GetMapping
	public Result getAll(@RequestParam Integer size){
		IPage<Stranger> page = new Page<>(1,size);
		return Result.success(strangerService.page(page,new QueryWrapper<Stranger>().orderByDesc("update_time")));
	}
}
