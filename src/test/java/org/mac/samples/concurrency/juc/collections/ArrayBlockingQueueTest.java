/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.juc.collections;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Mac
 * @create 2018-06-18 16:43
 **/

public class ArrayBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        //
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        /*queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.add("D");
        queue.add("E");
        System.out.println("size:5");
        queue.add("F");//java.lang.IllegalStateException: Queue full
        System.out.println("size:6");*/


        /*queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        queue.offer("D");
        queue.offer("E");
        System.out.println("size:5");
        queue.offer("F");//return false*/


       /* queue.put("A");
        queue.put("B");
        queue.put("C");
        queue.put("D");
        queue.put("E");
        System.out.println("size:5");
        queue.put("F");//blocking
        System.out.println("size:6");*/

        queue.put("A");
        queue.put("B");

        System.out.println(queue.poll());//A
        System.out.println(queue.poll());//B
        System.out.println(queue.poll());//null

        queue.put("A");
        queue.put("B");
        System.out.println(queue.peek());//A
        System.out.println(queue.peek());//A
        System.out.println(queue.peek());//A

        System.out.println(queue.take());//A
        System.out.println(queue.take());//B
        System.out.println(queue.take());//blocking

    }
}
