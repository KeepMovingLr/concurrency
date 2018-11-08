package com.enyi.concurrency.example.atomic;

import com.enyi.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@ThreadSafe
@Slf4j
public class AtomicExample5 {

    public volatile int count = 100;

    // 原子性的更新某个类中的Integer字段
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    public static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {
        if (updater.compareAndSet(example5, 100, 200)) {
            log.info("update success,{}", example5.count);
        } else {

        }
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update1 success,{}", example5.count);
        } else {
            log.info("update1 failed,{}", example5.count);

        }


    }


}
