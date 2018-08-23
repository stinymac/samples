/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.guarded_suspesion;

import java.util.LinkedList;

/**
 *
 * @author Mac
 * @create 2018-05-25 21:56
 **/

public class WaitQueue {

    private LinkedList<Request> queue = new LinkedList<>();

    public Request take () {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void put (Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
