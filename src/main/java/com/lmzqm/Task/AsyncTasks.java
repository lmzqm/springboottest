package com.lmzqm.Task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by lmzqm on 2017/4/25.
 *
 * http://www.jianshu.com/p/21f220c12199
 *
 * http://blog.csdn.net/blueheart20/article/details/44648667
 *
 * 异步调用，异步回调等的相关操作
 *
 * 同步调用：按照定义顺序依次执行，没一行程序都必须等待上一行程序执行完成之后才执行；
 * 异步调用：程序按顺序执行，不等待异步调用的语句返回结果就执行后面的程序
 *
 * @EnableAsync
 *
 * 使用Future<T>来返回异步
 *
 *
 *
 *
 *
 *
 */
@Component
public class AsyncTasks {

    public static Random random =  new Random();

    @Async
    public Future<String> doTaskOne() throws Exception{
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时："+(end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public  AsyncResult<String> doTaskTwo() throws Exception {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" +(end-start)+"毫秒");
        return new AsyncResult<>("完成任务二");

    }

    @Async
    public Future<String> doTaskThree() throws Exception{
        System.out.println("开始任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("任务三完成，耗时："+(end -start) + "毫秒");
        return new AsyncResult<>("完成任务三");
    }
}
