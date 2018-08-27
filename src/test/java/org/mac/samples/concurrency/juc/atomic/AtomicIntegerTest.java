/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Mac
 * @create 2018-06-11 14:49
 **/

public class AtomicIntegerTest {
    public static void main(String[] args) {
        //create
        AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());
        i = new AtomicInteger(10);
        System.out.println(i.get());
        //set
        i.set(1);
        System.out.println(i.get());

        i.lazySet(100);
        //get and and
        AtomicInteger j = new AtomicInteger(10);
        System.out.println(j.getAndAdd(10));
        System.out.println(j.get());
        System.out.println(j.getAndSet(1));
        //CAS
        System.out.println(j.compareAndSet(2,100));
        System.out.println(j.get());

    }
}
