package com.enyi.concurrency.example.synchronizedExample;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    // 修饰代码块
    public void test1() {
        // 锁this是一个对象，则同步作用于一个对象上
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1--{},{}", Thread.currentThread().getName(), i);
            }
        }
    }

    // 修饰一个方法
    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2--{},{}", Thread.currentThread().getName(), i);
        }

    }


    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        // 分别起两个线程执行
        executorService.execute(() -> {
            log.info("test----------------1");
            example1.test1();
        });
        executorService.execute(() -> {
            example2.test1();
        });
        log.info("shutdown");
        executorService.shutdown();
    }

}
