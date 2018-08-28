/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.juc.collections;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * @author Mac
 * @create 2018-06-18 23:02
 **/

public class LinkedBlockingDequeTest {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingDeque<String> linkedBlockingDequeue = new LinkedBlockingDeque();
        linkedBlockingDequeue.addFirst("A");
        linkedBlockingDequeue.addFirst("D");
        linkedBlockingDequeue.addLast("C");
        linkedBlockingDequeue.addFirst("B");

        Iterator<String> it = linkedBlockingDequeue.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println(linkedBlockingDequeue.take());
        System.out.println(linkedBlockingDequeue.take());
        System.out.println(linkedBlockingDequeue.take());
        System.out.println(linkedBlockingDequeue.take());
        //System.out.println(linkedBlockingDequeue.take());// blocking

        linkedBlockingDequeue.add("E");//==addLast();
    }
}
