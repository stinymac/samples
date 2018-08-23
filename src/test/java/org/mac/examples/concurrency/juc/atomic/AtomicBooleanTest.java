/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Mac
 * @create 2018-06-11 16:23
 **/

public class AtomicBooleanTest {
    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        System.out.println(atomicBoolean);
        atomicBoolean = new AtomicBoolean(true);
        System.out.println(atomicBoolean);

        AtomicBoolean b = new AtomicBoolean(true);

        boolean rst = b.compareAndSet(false,true);
        System.out.println("rst:"+rst);
    }
}
