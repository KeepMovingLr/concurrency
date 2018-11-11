package com.enyi.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author enyi.lr
 * @date 2018/11/11 12:57 PM
 * @description 在指定时间内执行的任务
 */
@Slf4j
public class CountDownLatchExample2 {
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
        countDownLatch.await(10, TimeUnit.MICROSECONDS);// 要求10 MICROSECONDS 内完成，若未完成，也不继续等待了
        log.info("finish");
        exec.shutdown();// 别忘记关闭线程池

    }

    private static void test(int threadNUm) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNUm);
        Thread.sleep(100);
    }
}
