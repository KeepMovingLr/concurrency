package com.enyi.concurrency.example.singleton;

import com.enyi.concurrency.annotations.ThreadSafe;

/**
 * @author enyi.lr
 * @date 2018/11/10 1:18 PM
 * @description 饿汉模式 单例实例在类装载时进行创建
 * 优点：线程安全
 * 缺点：如果构造方法中存在过多处理，会导致类加装比较慢，可能会有性能问题。如果加装了类却未使用，则会造成资源浪费。
 * 使用场景:构造类时，不需要太多复杂操作，并且此类在后续一定会被用到。
 */
@ThreadSafe
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2() {
    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();


    public static SingletonExample2 getInstance() {
        return instance;
    }
}
