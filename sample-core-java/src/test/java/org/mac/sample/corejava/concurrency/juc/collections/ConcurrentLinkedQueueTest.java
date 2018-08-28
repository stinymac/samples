/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.juc.collections;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Mac
 * @create 2018-06-19 22:58
 **/

public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {

        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();

        for (int i = 0; i < 100000; i++) {
            concurrentLinkedQueue.offer(System.nanoTime());
        }

        long startNanoTime = System.nanoTime();
        /*while (concurrentLinkedQueue.size() > 0) {
            concurrentLinkedQueue.poll();
        }*///18321 ms
        while (!concurrentLinkedQueue.isEmpty()) {
            concurrentLinkedQueue.poll();
        }//7 ms
        //@see #isEmpty(String str)
        System.out.println((System.nanoTime()-startNanoTime)/1000000+ " ms");

    }

    public static boolean isEmpty(String str) {
        //return null == str || "".equals(str);// a bad way
        return null == str || str.length() <= 0;
        //return null == str || str.isEmpty(); // same str.length() <= 0
    }
}
