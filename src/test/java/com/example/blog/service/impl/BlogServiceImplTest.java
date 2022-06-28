package com.example.blog.service.impl;

import com.example.blog.entity.result.Result;
import com.example.blog.service.BlogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceImplTest {

	@Resource(name = "blogService")
	private BlogService blogService;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void getBlogArchive() {
		Result blogArchive = blogService.getBlogArchive();
		System.out.println("111");
	}
}
