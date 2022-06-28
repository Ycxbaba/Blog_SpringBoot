package com.example.blog.entity.bean;

import lombok.Data;

import java.util.List;

@Data
public class ArchiveBlog {
	private Integer Date;
	private List<ArchiveBlog> archives;
	private List<Blog> blogs;
}
