package com.example.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步多线程启动定时器
 */
@Configuration
@EnableAsync
public class AsyncConfig {

	@Value("${async.corePoolSize}")
	private int corePoolSize = 10;
	@Value("${async.maxPoolSize}")
	private int maxPoolSize = 200;
	@Value("${async.queueCapacity}")
	private int queueCapacity = 10;

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.initialize();
		return executor;
	}
}
