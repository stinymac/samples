/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.juc.atomic;

/**
 *
 * @author Mac
 * @create 2018-06-11 22:16
 **/

public class AtomicFieldUpdaterTest {
    public static void main(String[] args) {
        AtomicFieldUpdater atomicFieldUpdater = new AtomicFieldUpdater();
        Object object = new Object();
        System.out.println(object);
        atomicFieldUpdater.update(object);
        System.out.println(atomicFieldUpdater.get());
    }
}
