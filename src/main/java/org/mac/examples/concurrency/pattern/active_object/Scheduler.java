/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.active_object;

/**
 *
 * @author Mac
 * @create 2018-06-08 15:17
 **/

public class Scheduler extends Thread{

    private ActivationQueue queue;

    public Scheduler(ActivationQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            queue.take().execute();
        }
    }
}
