package com.enyi.concurrency.example.singleton;

import com.enyi.concurrency.annotations.ThreadSafe;

/**
 * @author enyi.lr
 * @date 2018/11/10 1:58 PM
 * @description ${DESCRIPTION}
 */
@ThreadSafe
public class SingletonExample6 {

    public SingletonExample6() {
    }

    private volatile static SingletonExample6 instance = null;

    static {
        // notice: static block should be after: private volatile static SingletonExample6 instance = null;
        instance = new SingletonExample6();
    }

    private static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }


}
