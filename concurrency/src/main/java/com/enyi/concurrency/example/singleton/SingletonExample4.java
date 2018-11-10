package com.enyi.concurrency.example.singleton;

import com.enyi.concurrency.annotations.NotThreadSafe;

/**
 * @author enyi.lr
 * @date 2018/11/10 1:27 PM
 * @description 一直以为是线程安全的，实际是线程不安全的
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample4 {

    private SingletonExample4() {
    }
    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // JVM和cpu优化，发生了指令重排
    // 假设重排为如下：
    // 1、memory = allocate() 分配对象的内存空间
    // 3、instance = memory 设置instance指向刚分配的内存
    // 2、ctorInstance() 初始化对象
    // if Thread A execute to "instance = new SingletonExample4();"
    // and Thread B execute to first"if (instance == null) {"
    // after指令重排 A如若刚执行 instance = memory 设置instance指向刚分配的内存 then Thread B "if (instance == null) {" is true
    // and return a null instance.

    private static SingletonExample4 instance = null;

    private static SingletonExample4 getInstance() {

        if (instance == null) { // 双重检测机制
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }

}
