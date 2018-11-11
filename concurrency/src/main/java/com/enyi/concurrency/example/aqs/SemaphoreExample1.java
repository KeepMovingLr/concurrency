package com.enyi.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author enyi.lr
 * @date 2018/11/11 1:16 PM
 * @description ${DESCRIPTION}
 */
@Slf4j
public class SemaphoreExample1 {

    private final static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire(); //acquire
                    test(threadNum);
                    semaphore.release(); // release
                } catch (InterruptedException e) {
                    log.error("exception", e);
                } finally {
                    // 这里为了保证一定执行，用了finally
                }
            });

        }
        exec.shutdown();// 别忘记关闭线程池

    }

    private static void test(int threadNUm) throws InterruptedException {
        log.info("{}", threadNUm);
        Thread.sleep(1000);
    }
}
