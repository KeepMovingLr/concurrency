package com.enyi.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newScheduledThreadPool(10);
        //((ScheduledExecutorService) executorService).scheduleAtFixedRate(() -> log.warn("schedule run"), 1, 3, TimeUnit.SECONDS);
        ((ScheduledExecutorService) executorService).schedule(() -> log.warn("schedule run2"),2,TimeUnit.SECONDS);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 5 * 1000);
    }


}
