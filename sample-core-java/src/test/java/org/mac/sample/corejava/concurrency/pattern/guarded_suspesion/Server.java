/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.guarded_suspesion;

import java.util.Random;

/**
 *
 * @author Mac
 * @create 2018-05-25 22:06
 **/

public class Server extends Thread{
    private final WaitQueue queue;

    private final Random random;

    private volatile boolean shutdown = false;

    public Server(WaitQueue queue) {
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!shutdown) {
            Request request = queue.take();
            if (null == request) {
                System.out.println("Received the empty request.");
                continue;
            }
            System.out.println("Server ->" + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void close() {
        this.shutdown = true;
        this.interrupt();
    }
}
