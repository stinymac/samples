/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 *
 * @author Mac
 * @create 2018-06-11 21:42
 **/

public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        Simple simple = new Simple();

        AtomicIntegerFieldUpdater<Simple> updater = AtomicIntegerFieldUpdater.newUpdater(Simple.class,"i");

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 20; j++) {
                   int v =  updater.getAndIncrement(simple);
                   System.out.println(Thread.currentThread().getName()+ "->" + v);
                }
            }).start();
        }

    }

    static class Simple {
        volatile int i;
    }
}
