package com.example.blog.entity.query;

import lombok.Data;

@Data
public class QueryComment {
	private Integer blogId;
	private Boolean deleted;
	private Boolean author;

	private Boolean admin;

	private Integer day;
}
