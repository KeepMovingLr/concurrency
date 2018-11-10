package com.enyi.concurrency.example.singleton;

import com.enyi.concurrency.annotations.ThreadSafe;

/**
 * @author enyi.lr
 * @date 2018/11/10 1:55 PM
 * @description
 */
@ThreadSafe
public class SingletonExample5 {

    public SingletonExample5() {
    }

    // 单例对象 volatile + 双重检测机制 -> 禁止指令重排
    private volatile static SingletonExample5 instance = null;

    private static SingletonExample5 getInstance() {

        if (instance == null) { // 双重检测机制
            synchronized (SingletonExample5.class) {
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }

}
