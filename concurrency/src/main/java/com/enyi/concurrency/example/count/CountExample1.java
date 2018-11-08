package com.enyi.concurrency.example.count;

import com.enyi.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@NotThreadSafe
@Slf4j
public class CountExample1 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 同时能够并发执行的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("Exception", e);
                }
                countDownLatch.countDown();// countdown 1 each call
            });
        }
        countDownLatch.await();
        // 关闭线程
        executorService.shutdown();
        // 以上代码还是略不理解  一周后理解了
        log.info("count:{}", count);

    }

    /**
     * 本质上应该是这个方法线程不安全
     */
    private static void add() {
        count++;
    }
}
