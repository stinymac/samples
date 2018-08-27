/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.juc.collections;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Mac
 * @create 2018-06-18 17:34
 **/

public class LinkedBlockingQueueTest {
    // bounds can choice //Integer.MAX_VALUE
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.add("A");

        System.out.println(linkedBlockingQueue.take());
        System.out.println(linkedBlockingQueue.take());//blocking
    }



}
