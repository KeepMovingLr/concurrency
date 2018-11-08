package com.enyi.concurrency.example.atomic;

import com.enyi.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


@ThreadSafe
@Slf4j
public class AtomicExample6 {

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    private static Boolean isHappendUnsafe = false;
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 同时能够并发执行的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test2();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("Exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappened:{}", isHappened.get());
        log.info("isHappened2:{}", isHappendUnsafe);

    }

    // 由于是原子性的，则当false变为true的时候，只执行一次，当需要只执行一次代码时，可以用这个
    private static void test() {
        if (isHappened.compareAndSet(false, true)) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("execute");
        }
    }

    // 线程不安全
    private static void test2() {
        if (!isHappendUnsafe) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isHappendUnsafe = true;
            log.info("execute");
        }
    }


}
