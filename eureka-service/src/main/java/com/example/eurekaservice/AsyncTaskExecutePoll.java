package com.example.eurekaservice;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
@Configuration
public class AsyncTaskExecutePoll implements AsyncConfigurer {
    @Autowired
    private TaskThreadPoolConfig config;

    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(config.getCorePoolSize());
        executor.setMaxPoolSize(config.getMaxPoolSize());
        executor.setQueueCapacity(config.getQueueCapacity());
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        executor.setThreadNamePrefix(config.getThreadNamePrefix());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
    //AbortPolicy ：直接抛出java.util.concurrent.RejectedExecutionException 异常。
    //• CallerRunsPolicy ：主线程直接执行该任务，执行完之后尝试添加下一个任务到线程
    //池中，这样可以有效降低向线程池内添加任务的速度。
    //建议大家用CallerRunsPolicy 策略，因为当队列中的任务满了之后，如果直接抛异常，
    //那么这个任务就会被丢弃。如果是CallerRunsPolicy 策略，则会用主线程去执行，就是同步
    //执行，这样最起码任务不会被丢弃。

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptinHandler(){
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                System.out.println("error :" + throwable.getMessage());
            }
        };
    }
}
