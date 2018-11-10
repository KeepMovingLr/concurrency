package com.enyi.concurrency.example.publish;

import com.enyi.concurrency.annotations.NotRecommend;
import com.enyi.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author enyi.lr
 * @date 2018/11/10 12:29 AM
 * @description ${DESCRIPTION}
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        // 在构造Escape的过程中，就构造了InnerClass。
        // 但在InnerClass中，使用了Escape.this.thisCanBeEscape，没有等Escape构造完就使用，存在不安全因素。
        new Escape();
    }
}
