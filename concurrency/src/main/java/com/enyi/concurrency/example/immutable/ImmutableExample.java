package com.enyi.concurrency.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author enyi.lr
 * @date 2018/11/10 5:55 PM
 * @description ${DESCRIPTION}
 */
@Slf4j
public class ImmutableExample {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();
        // final de map can be put another value
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    // final 的入参不能被修改
    private void test(final int a) {
//        a = 1;
    }


}
