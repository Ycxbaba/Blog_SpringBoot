package com.example.blog.entity.query;

import lombok.Data;

@Data
public class QueryType {
	private Integer deleted = 0;
	private Integer pageSize;
	private Integer pageNum;
}
