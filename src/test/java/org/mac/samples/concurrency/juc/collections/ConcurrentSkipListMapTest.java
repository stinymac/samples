/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.juc.collections;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-19 22:04
 **/

public class ConcurrentSkipListMapTest {

    public static void main(String[] args) {

        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();

        concurrentSkipListMap.put(1,"A");
        concurrentSkipListMap.put(4,"B");
        concurrentSkipListMap.put(5,"C");
        concurrentSkipListMap.put(6,"D");

        concurrentSkipListMap.merge(1,"Z",(ov,v)->{
            return ov + "-" + v;
        });

        System.out.println(concurrentSkipListMap.get(1));

        concurrentSkipListMap.compute(1,(k,v)->{
            System.out.println(k + ":"+v);
            return "";
        });

    }
}
