package com.enyi.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author enyi.lr
 * @date 2018/11/11 4:51 PM
 * @description
 */
@Slf4j
public class LockExample3 {

    private final Map<String, Data> map = new TreeMap<>();

    public static int count = 0;

    // private final static Lock lock = new Reentr();

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    // 读取的时候用读锁
    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    // 读取的时候用读锁
    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    // 写请求加上写锁。其他读写锁都不在的时候，才能加上写锁。读写锁，如果读太多，会导致写饥饿。
    public Data put(String key, Data value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    class Data {

    }

}
