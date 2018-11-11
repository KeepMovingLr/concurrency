package com.enyi.concurrency.example.aqs;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author enyi.lr
 * @date 2018/11/11 12:48 PM
 * @description CountDownLatch使用
 */
@Slf4j
public class CountDownLatchExample1 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception", e);
                } finally {
                    // 这里为了保证一定执行，用了finally
                    countDownLatch.countDown();
                }
            });

        }
        // 调用了await()就被等待了，只有countDown的计数器减为0，才能继续执行
        countDownLatch.await();// 能保证所有的线程都执行完再执行finish
        log.info("finish");
        exec.shutdown();// 别忘记关闭线程池

    }

    private static void test(int threadNUm) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNUm);
        Thread.sleep(100);
    }

}
