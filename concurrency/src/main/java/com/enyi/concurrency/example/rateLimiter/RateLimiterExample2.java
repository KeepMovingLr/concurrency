package com.enyi.concurrency.example.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author enyi.lr
 * @date 2018/11/18 10:23 PM
 * @description 限流操作
 */
@Slf4j
public class RateLimiterExample2 {

    //rate is "5 permits per second" 200ms put a token
    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) throws InterruptedException {
        for (int index = 0; index < 100; index++) {
            double acquire = rateLimiter.acquire();
            handle(index);
        }
    }

    private static void handle(int i) {
        log.info("{}", i);
    }

}
