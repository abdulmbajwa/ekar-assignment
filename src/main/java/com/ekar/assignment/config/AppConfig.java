package com.ekar.assignment.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Configuration
public class AppConfig {

    @Bean
    public ExecutorService consumerExecutor() {
        BasicThreadFactory factory = new BasicThreadFactory.Builder()
                .namingPattern("Consumer-%d")
                .daemon(true)
                .priority(Thread.MAX_PRIORITY)
                .build();
        return Executors.newCachedThreadPool(factory);
    }

    @Bean
    public ExecutorService producerExecutor() {
        BasicThreadFactory factory = new BasicThreadFactory.Builder()
                .namingPattern("Producer-%d")
                .daemon(true)
                .priority(Thread.MAX_PRIORITY)
                .build();
        return Executors.newCachedThreadPool(factory);
    }


    @Bean
    public ThreadPoolTaskExecutor monitorExecutor() {
        return new TaskExecutorBuilder().threadNamePrefix("Monitor-").corePoolSize(1).maxPoolSize(1).build();
    }
}
