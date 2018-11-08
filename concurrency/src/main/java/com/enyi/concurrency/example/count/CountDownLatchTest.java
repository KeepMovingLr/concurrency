package com.enyi.concurrency.example.count;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
@Slf4j
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        log.error("before count:{}", countDownLatch.getCount());
        countDownLatch.countDown();
        log.error("after count:{}", countDownLatch.getCount());
        countDownLatch.countDown();
        log.error("after count:{}", countDownLatch.getCount());
    }


}
