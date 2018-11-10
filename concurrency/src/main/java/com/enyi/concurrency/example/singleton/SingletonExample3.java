package com.enyi.concurrency.example.singleton;

import com.enyi.concurrency.annotations.NotRecommend;
import com.enyi.concurrency.annotations.ThreadSafe;

/**
 * @author enyi.lr
 * @date 2018/11/10 1:25 PM
 * @description 线程安全，但性能差.懒汉模式，单例在第一次使用时被初始化。
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    private SingletonExample3() {
    }

    private static SingletonExample3 instance = null;

    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
