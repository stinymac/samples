/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.producer_consumer;

/**
 *
 * @author Mac
 * @create 2018-06-06 15:25
 **/

public class ProducerConsumerClient {
    public static void main(String[] args) {
        final MessageQueue messageQueue = new MessageQueue();
        new Producer(messageQueue, 1).start();
        new Producer(messageQueue, 2).start();
        new Producer(messageQueue, 3).start();
        new Consumer(messageQueue, 1).start();
        new Consumer(messageQueue, 2).start();
    }
}
