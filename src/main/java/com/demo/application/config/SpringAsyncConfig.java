package com.demo.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
 * 
 * 
 * 开启多线程任务
 * 
 * 
 * 池超凡
 * 
 * 2015年6月26日 下午11:28:51
 * 
 * @version 1.0.0
 *
 */
@Configuration  
@EnableAsync(annotation = Async.class)
public class SpringAsyncConfig {
    @Value("${pool.size:30}")
    private int poolSize;;

    @Value("${queue.capacity:0}")
    private int queueCapacity;
    @Bean(name="workExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(poolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }
}
