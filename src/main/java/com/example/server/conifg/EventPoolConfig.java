package com.example.server.conifg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <功能描述> 事件使用的线程池
 *
 * @author
 * @date 2021/1/4 10:30
 */
@EnableAsync
@Configuration
public class EventPoolConfig {

    private static final Integer CPU_CORE_NUM = Runtime.getRuntime().availableProcessors();
    /**
     * 核心线程数
     */
    private static final Integer CORE_POOL_SIZE = 20;
    /**
     * 最大线程数
     */
    private static final Integer MAX_POOL_SIZE = 40;
    /**
     * 队列大小
     */
    private static final Integer QUEUE_POOL_SIZE = 100;
    /**
     * 存活时间 秒
     */
    private static final Integer KEEP_ALIVE_SECONDS = 60;
    /**
     * 线程名前缀
     */
    private static final String THREAD_NAME_PREFIX = "eventExecutor-";

    private static final String LOG_THREAD_NAME_PREFIX = "logExecutor-";

    @Bean("eventExecutor")
    public Executor eventExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_POOL_SIZE);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean("logExecutor")
    public Executor logExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CPU_CORE_NUM * 2);
        executor.setMaxPoolSize(CPU_CORE_NUM * 3);
        executor.setQueueCapacity(QUEUE_POOL_SIZE * 5);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setThreadNamePrefix(LOG_THREAD_NAME_PREFIX);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
