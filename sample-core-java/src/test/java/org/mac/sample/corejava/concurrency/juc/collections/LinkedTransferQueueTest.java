/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.juc.collections;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mac
 * @create 2018-06-18 23:33
 **/

public class LinkedTransferQueueTest {

    public static void main(String[] args) throws InterruptedException {

        // make sure data consumed

        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();

        System.out.println(linkedTransferQueue.add("X"));//true
        System.out.println(linkedTransferQueue.size());//1
        System.out.println("=============");

        linkedTransferQueue.clear();

        System.out.println(linkedTransferQueue.tryTransfer("A"));//false

        System.out.println(linkedTransferQueue.size());//0

        System.out.println(linkedTransferQueue.peek());//null


        new Thread(()->{
            try {
                linkedTransferQueue.transfer("C");//blocking
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(100);

        new Thread(()->{
            while (true) {
                try {
                    System.out.println("take:"+linkedTransferQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(100);
        linkedTransferQueue.add("B");
        System.out.println(linkedTransferQueue.size());

    }
}
