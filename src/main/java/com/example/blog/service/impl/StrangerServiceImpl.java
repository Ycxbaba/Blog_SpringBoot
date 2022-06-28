package com.example.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.bean.Stranger;
import com.example.blog.service.StrangerService;
import com.example.blog.mapper.StrangerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 24933
* @description 针对表【t_stranger】的数据库操作Service实现
* @createDate 2022-05-25 21:00:08
*/
@Service(value = "strangerService")
public class StrangerServiceImpl extends ServiceImpl<StrangerMapper, Stranger>
    implements StrangerService{

	@Resource(name = "strangerMapper")
	private StrangerMapper strangerMapper;

}




