/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.juc.collections;

import java.util.concurrent.PriorityBlockingQueue;

/**
 *
 * @author Mac
 * @create 2018-06-18 17:19
 **/

public class PriorityBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        //public PriorityBlockingQueue(int initialCapacity,Comparator<? super E> comparator)
        // must implements Comparable or offer a comparator
        PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();
        // add() == offer() == put()
        priorityBlockingQueue.add("B");
        priorityBlockingQueue.add("C");
        priorityBlockingQueue.add("A");
        priorityBlockingQueue.add("D");
        priorityBlockingQueue.add("D");

        System.out.println(priorityBlockingQueue.peek());
        System.out.println(priorityBlockingQueue.size());//5
        System.out.println(priorityBlockingQueue.element());
        System.out.println(priorityBlockingQueue.size());//5

        System.out.println(priorityBlockingQueue.poll());//A
        System.out.println(priorityBlockingQueue.poll());//B
        System.out.println(priorityBlockingQueue.poll());//C
        System.out.println(priorityBlockingQueue.poll());//D
        System.out.println(priorityBlockingQueue.poll());//D
        System.out.println(priorityBlockingQueue.size());//0

        System.out.println(priorityBlockingQueue.take());//blocking
    }
}
