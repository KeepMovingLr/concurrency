package com.enyi.concurrency.example.singleton;

/**
 * @author enyi.lr
 * @date 2018/11/10 2:02 PM
 * @description 枚举模式：最安全
 */

import com.enyi.concurrency.annotations.Recommend;
import com.enyi.concurrency.annotations.ThreadSafe;


@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有构造函数
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        // 把想要单例的对象当作枚举的一个变量
        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }


}
