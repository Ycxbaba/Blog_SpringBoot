package com.example.blog.entity.query;

import lombok.Data;

@Data
public class QueryMessage {
	private Integer deleted;
	private Integer pageNum;
	private Integer pageSize;
	private Integer qq;
}
