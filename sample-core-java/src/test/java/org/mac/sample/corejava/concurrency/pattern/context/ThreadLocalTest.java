/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.context;

/**
 *
 * @author Mac
 * @create 2018-06-22 9:40
 **/

public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal tl = new ThreadLocal();
        /*tl.set(1);
        tl.set(2);
        System.out.println(tl.get());*/
        Object obj = new Object();
        System.out.println(obj);
        new Thread(()->{
            tl.set(obj);
            System.out.println(tl.get());
        }).start();
        new Thread(()->{
            tl.set(obj);
            System.out.println(tl.get());
        }).start();
    }
}
