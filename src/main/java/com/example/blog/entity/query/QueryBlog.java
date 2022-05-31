package com.example.blog.entity.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryBlog {
	private Integer pageNum = 1;
	private Integer pageSize = 6;
	private Integer typeId = null;
	private String title = null;
	private Boolean deleted = null;
	private Boolean publish = null;
	private Boolean original = null;
	private Boolean recommend = null;
	//排序
	private Boolean latest = true;
	private Boolean mostLike = null;
	private Boolean mostView = null;
	//时间筛选
	private Date lTime = null;
	private Date rTime = null;
}
