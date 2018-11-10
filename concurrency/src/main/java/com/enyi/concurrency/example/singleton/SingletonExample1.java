package com.enyi.concurrency.example.singleton;

import com.enyi.concurrency.annotations.NotRecommend;
import com.enyi.concurrency.annotations.NotThreadSafe;

/**
 * @author enyi.lr
 * @date 2018/11/10 1:15 PM
 * @description 懒汉式，线程不安全
 */
@NotThreadSafe
@NotRecommend
public class SingletonExample1 {

    // 构造函数私有化，阻止外面的函数不断new出新的对象。
    private SingletonExample1() {

    }

    // 得提供一个访问方式
    private static SingletonExample1 instance = null;

    public static SingletonExample1 getInstance() {
        // 这里可能有线程安全问题，如果方法在构造时需要做很多操作，如果运行了多次，可能出现错误。
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
