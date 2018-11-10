package com.enyi.concurrency.example.threadLocal;

/**
 * @author enyi.lr
 * @date 2018/11/10 6:46 PM
 * @description
 */
public class RequestHolder {

    public static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    // 在请求实际进入服务器，但是还没有进行相关处理的时候使用，可以考虑使用filter
    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    // 要提供一个remove方法，若不remove，则会线程泄露
    // 调用时机，在接口处理完成后调用，考虑interceptor
    public static void remove() {
        requestHolder.remove();
    }

}
