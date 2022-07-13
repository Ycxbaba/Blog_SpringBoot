package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.Role;
import com.example.blog.entity.bean.User;
import com.example.blog.entity.result.Result;
import com.example.blog.exception.CommonException;
import com.example.blog.mapper.UserMapper;
import com.example.blog.service.UserService;
import com.example.blog.utils.JwtUtils;
import com.example.blog.utils.RedisUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author 24933
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-05-10 21:16:49
*/
@Service(value = "userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

	@Resource(name = "userMapper")
	private UserMapper userMapper;

	@Resource(name = "redisUtils")
	private RedisUtils redisUtils;

	@Resource(name = "jwtUtils")
	private JwtUtils jwtUtils;

	/**
	 * 逻辑删除
	 * @param id 用户id
	 */
	@Override
	public Result delUserById(Integer id) {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.set("deleted",1);
		updateWrapper.eq("id",id);
		boolean update = super.update(updateWrapper);
		if (!update){
			throw new CommonException("600","删除失败");
		}
		return Result.success("删除成功",null);
	}

	/**
	 * 更改密码
	 * @param user 用户信息
	 */
	@Override
	public Result updatePw(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode(user.getPassword());
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id",user.getId())
				.set("password",password);
		boolean update = super.update(updateWrapper);
		if (!update){
			throw new CommonException("600","修改失败");
		}
		//退出重新登录
		redisUtils.del("user" + user.getId());
		return Result.success("修改成功,请重新登录",null);
	}

	/**
	 * 查询所有的用户
	 */
	@Override
	public Result getAllUser() {
		List<User> list = userMapper.selectAll();
		return Result.success(list);
	}

	/**
	 * 超级管理员修改用户信息
	 * @param user 用户信息
	 */
	@Override
	@Transactional
	public Result updateUser(User user) {
		if (userMapper.updateById(user) == 0 || !userMapper.setRole(user.getRole().getId(),user.getId())) {
			throw new CommonException("600","修改失败");
		}
		//下线操作
		redisUtils.del("user" + user.getId());

		return Result.success("修改成功",null);
	}

	@Override
	public Result getSelf(Integer id, String token) {
		if(!checkToken(token,id)){
			throw new CommonException("403","权限不足");
		}
		User user = userMapper.selectSelf(id);
		return Result.success(user);
	}

	@Override
	public Result getAllRoles() {
		List<Role> roles = userMapper.getAllRoles();
		return Result.success(roles);
	}

	@Override
	public Result recover(Integer id) {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.set("deleted",0);
		updateWrapper.eq("id",id);
		boolean update = super.update(updateWrapper);
		if (!update){
			throw new CommonException("600","修改失败");
		}
		return Result.success("修改成功",null);
	}

	@Override
	@Transactional
	public Result saveUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode(user.getPassword());
		user.setPassword(password);
		super.save(user);
		userMapper.saveUR(user.getId(),user.getRole().getId());
		return Result.success("新增成功",null);
	}

	@Override
	@Transactional
	public Result deleteById(Integer id) {
		userMapper.deleteUR(id);
		if (!super.removeById(id)) {
			throw new CommonException("600","删除失败");
		}
		return Result.success("删除成功",null);
	}

	/**
	 * 修改用户自己信息
	 * @param user 用户信息
	 * @param token 用于验证
	 */
	@Override
	public Result updateUser(User user, String token) {
		if(!checkToken(token,user.getId())){
			throw new CommonException("403","权限不足");
		}
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id",user.getId())
				.set("nickname",user.getNickname())
				.set("avatar",user.getAvatar())
				.set("update_time",new Date());
		boolean update = super.update(updateWrapper);
		if(!update){
			throw new CommonException("600","修改失败");
		}
		return Result.success("修改成功",null);
	}

	/**
	 * 检查用户token中的id和请求域中的id是否一致
	 * @param token token
	 * @param userId id
	 */
	private boolean checkToken(String token,Integer userId){
		String s = jwtUtils.parseToken(token).getSubject();
		int id = Integer.parseInt(s);
		return id == userId && redisUtils.hasKey("user" + id);
	}
}




