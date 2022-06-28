package com.example.blog.controller.admin;

import com.example.blog.entity.bean.Type;
import com.example.blog.entity.query.QueryType;
import com.example.blog.entity.result.Result;
import com.example.blog.service.TypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/type")
public class AdminTypeController {

	@Resource(name = "typeService")
	private TypeService typeService;

	/**
	 * 后台查询--不做缓存
	 */
	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@GetMapping("/list")
	public Result getTypes(){
		return typeService.getTypes(null);
	}

	/**
	 * 修改 | 新增
	 * @param type
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@PostMapping("/save")
	public Result saveType(@RequestBody Type type){
		return typeService.saveType(type);
	}

	/**
	 * 逻辑删除
	 * @param id id
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@PostMapping("/delete/{id}")
	public Result delType(@PathVariable("id") int id){
		return typeService.delType(id);
	}

	/**
	 * 永久删除
	 * @param id id
	 * @return
	 */
	@PreAuthorize("hasAuthority('superAdmin')")
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable("id") int id){
		return typeService.delete(id);
	}
}
