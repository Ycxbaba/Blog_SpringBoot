package com.example.blog.controller.admin;

import com.example.blog.entity.bean.User;
import com.example.blog.entity.result.Result;
import com.example.blog.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

	@Resource(name = "userService")
	private UserService userService;

	@PreAuthorize("hasAuthority('superAdmin')")
	@DeleteMapping("/del/{id}")
	public Result delUser(@PathVariable("id") Integer id){
		return userService.delUserById(id);
	}

	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@PutMapping("/update/pw")
	public Result updatePw(@RequestBody User user){
		return userService.updatePw(user);
	}

	@PreAuthorize("hasAuthority('superAdmin')")
	@GetMapping("/all")
	public Result getAllUser(){
		return userService.getAllUser();
	}

	@PreAuthorize("hasAnyAuthority('superAdmin','admin')")
	@PutMapping("/update/normal")
	public Result updateUserNormal(@RequestBody User user,@RequestHeader("token") String token){
		return userService.updateUser(user,token);
	}

	@PreAuthorize("hasAuthority('superAdmin')")
	@PutMapping("/update/super")
	public Result updateUserSuper(@RequestBody User user){
		return userService.updateUser(user);
	}


}
