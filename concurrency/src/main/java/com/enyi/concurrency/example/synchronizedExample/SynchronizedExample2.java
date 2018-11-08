package com.enyi.concurrency.example.synchronizedExample;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample2 {

    // synchronized修饰静态方法中的代码块，同步作用于类上
    public static void test1() {
        // 锁this是一个对象，则同步作用于一个对象上 把锁换成SynchronizedExample1.class看看什么情况
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1--{},{}", Thread.currentThread().getName(), i);
            }
        }
    }

    // 修饰静态方法 锁默认是SynchronizedExample2.class
    public static synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2--{},{}", Thread.currentThread().getName(), i);
        }

    }

    public static void test3() {
        // 锁this是一个对象，则同步作用于一个对象上 把锁换成SynchronizedExample1.class看看什么情况
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1--{},{}", Thread.currentThread().getName(), i);
            }
        }
    }

    // 总结：synchronized修饰一个类的静态方法，锁默认是本类的字节码对象，持有同一个锁的静态方法都互斥
    // 若修饰一个静态方法中的一个代码块，则传入本类的字节码文件，持有同一个锁的所有静态方法都互斥，也许静态方法不同
    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        SynchronizedExample2 example3 = new SynchronizedExample2();
        // 分别起两个线程执行
        executorService.execute(() -> {
            log.info("test----------------1");
            example1.test1();
        });
        executorService.execute(() -> {
            log.info("test----------------2");
            example2.test2();
        });
        executorService.execute(() -> {
            log.info("test----------------3");
            example3.test3();
        });
        log.info("shutdown");
        executorService.shutdown();
    }

}
