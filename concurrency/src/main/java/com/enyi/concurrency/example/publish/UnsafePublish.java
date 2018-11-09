package com.enyi.concurrency.example.publish;

import com.enyi.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] status = {"1", "2", "3"};

    public String[] getStatus() {
        return status;
    }

    public static void main(String[] args) {
        // 类的任何外部线程可以访问到status，这样的发布对象不安全，
        // 因为无法确定其他线程是否修改了status，当使用status时候，不确定里面的值。
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStatus()));
        unsafePublish.getStatus()[0] = "-1";
        log.info("{}", Arrays.toString(unsafePublish.getStatus()));
    }


}
