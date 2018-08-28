/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.active_object;

import java.util.LinkedList;

/**
 *
 * @author Mac
 * @create 2018-06-08 15:14
 **/

public class ActivationQueue {
    private final static int MAX_METHOD_OBJECT_QUEUE_SIZE = 100;

    private final LinkedList<MethodObject> methodQueue;

    public ActivationQueue() {
        methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodObject methodObject) {
        while (methodQueue.size() >= MAX_METHOD_OBJECT_QUEUE_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.methodQueue.addLast(methodObject);
        this.notifyAll();
    }

    public synchronized MethodObject take() {
        while (methodQueue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        MethodObject methodObject = methodQueue.removeFirst();
        this.notifyAll();
        return methodObject;
    }
}
