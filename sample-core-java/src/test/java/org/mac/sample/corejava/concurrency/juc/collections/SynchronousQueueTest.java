/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.juc.collections;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mac
 * @create 2018-06-18 18:20
 **/

public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {
        // A synchronous queue does not have any
        // internal capacity, not even a capacity of one.
        SynchronousQueue synchronousQueue = new SynchronousQueue();

        // synchronousQueue.put("B");//blocking

        new Thread(() -> {
            try {
                while (true) {
                    String v = (String) synchronousQueue.take();
                    if (v != null) {
                        System.out.println(v);
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        //  java.lang.IllegalStateException: Queue full
        //  you cannot insert an element (using any method)
        //  unless another thread is trying to remove it
        synchronousQueue.add("A");
    }
}
